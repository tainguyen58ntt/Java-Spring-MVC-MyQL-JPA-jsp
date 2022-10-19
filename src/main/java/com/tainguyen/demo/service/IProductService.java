package com.tainguyen.demo.service;

import java.util.List;
import java.util.Optional;

import com.tainguyen.demo.model.Product;

public interface IProductService {
	List<Product> findProductByCategoryID(String categoryID);

	Optional<Product> findById(String productId);
	
	void save(Product p);
	
	void deleteById(String id);
}
