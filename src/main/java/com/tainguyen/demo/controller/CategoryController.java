package com.tainguyen.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tainguyen.demo.model.Category;
import com.tainguyen.demo.service.ICategoryService;

@Controller
@RequestMapping(path = "categories")
//http:localhost:8080/categories
public class CategoryController {
    @Autowired //Inject "categoryRepository" - Dependency Injection
//    private CategoryRepository categoryRepository;
    private ICategoryService categoryService;
    //return name of "jsp file"
    //http:localhost:8083/categories
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap) {
        //data sent to jsp => ModelMap
        //send data from Controller to View
        //modelMap.addAttribute("name", "Hoang");
        //modelMap.addAttribute("age", 18);
        Iterable<Category> categories = categoryService.findAllCategory();
        modelMap.addAttribute("categories", categories);
        System.out.println("haha");
        return "category";
    }
}