package com.tainguyen.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tainguyen.demo.model.Category;
import com.tainguyen.demo.model.Product;
import com.tainguyen.demo.service.ICategoryService;
import com.tainguyen.demo.service.IProductService;
import com.tainguyen.demo.service.ProductService;

@Controller
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
//    ProductRepository productRepository;
    private IProductService productService;
    @Autowired //Inject "categoryRepository" - Dependency Injection
//    private CategoryRepository categoryRepository;
    private ICategoryService categoryService;
    //http:localhost:8083/products/getProductsByCategoryID/C0103
    @RequestMapping(value = "/getProductsByCategoryID/{categoryID}", method = RequestMethod.GET)
    public String getProductsByCategoryID(ModelMap modelMap, @PathVariable String categoryID) {
        Iterable<Product> products = productService.findProductByCategoryID(categoryID);
        modelMap.addAttribute("products", products);
        return "productList"; //"productList.jsp"
    }
    @RequestMapping(value = "/changeCategory/{productID}", method = RequestMethod.GET)
    public String changeCategory(ModelMap modelMap,  @PathVariable String productID) {
        modelMap.addAttribute("categories", categoryService.findAllCategory());
        modelMap.addAttribute("product", productService.findById(productID).get());
        return "updateProduct";//updateProduct.jsp
    }
    @RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
    public String insertProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryService.findAllCategory());
        return "insertProduct";//insertProduct.jsp
    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    //method overloading
    public String insertProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult //validation
                                ) {
        if(bindingResult.hasErrors()) {
            return "insertProduct";
        }
        try {
            //random uuid => productID
            //String productID, String categoryID, String productName, int price, String description
            product.setProductID(UUID.randomUUID().toString());
            productService.save(product);
            return "redirect:/categories";
        }catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "insertProduct";
        }
    }

    @RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
    public String deleteProduct(ModelMap modelMap, @PathVariable String productID) {
        productService.deleteById(productID);
        return "redirect:/categories";
    }
    ///products/updateProduct/${product.getProductID()}
    @RequestMapping(value = "/updateProduct/{productID}", method = RequestMethod.POST)
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @PathVariable String productID
    ) {
        Iterable<Category> categories = categoryService.findAllCategory();
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categories);
            return "updateProduct";//updateProduct.jsp
        }
        try {
            if(productService.findById(productID).isPresent()) {
                Product foundProduct = productService
                        .findById(product.getProductID()).get();
                if(!product.getProductName().trim().isEmpty()) {
                    foundProduct.setProductName(product.getProductName() );
                }
                if(!product.getCategoryID().isEmpty()) {
                    foundProduct.setCategoryID(product.getCategoryID());
                }
                //is empty => "" OR NULL
                if(!product.getDescription().trim().isEmpty()) {
                    foundProduct.setDescription(product.getDescription());
                }
                if(product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                productService.save(foundProduct);
            }
        }catch (Exception e) {
            return "updateProduct";//updateProduct.jsp
        }
        return "redirect:/products/getProductsByCategoryID/"+product.getCategoryID();
    }


}
