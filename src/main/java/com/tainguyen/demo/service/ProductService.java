package com.tainguyen.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tainguyen.demo.model.Product;
import com.tainguyen.demo.repositories.ProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> findProductByCategoryID(String categoryID) {
		// TODO Auto-generated method stub

		return productRepo.findByCategoryID(categoryID);

	}

	@Override
	public Optional<Product> findById(String productId) {
		// TODO Auto-generated method stub
		
		return productRepo.findById(productId);
	}

	@Override
	public void save(Product p) {
		// TODO Auto-generated method stub
		productRepo.save(p);
	}

	@Override
	public void deleteById(String id) {
		productRepo.deleteById(id);
		
	}	
	

}
