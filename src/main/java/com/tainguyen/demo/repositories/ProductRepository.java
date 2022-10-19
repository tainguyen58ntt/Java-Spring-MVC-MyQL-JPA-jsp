package com.tainguyen.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tainguyen.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	// chi can viet dung ten method vay thoi
	// la spring jpa tu lam cho minh
	List<Product> findByCategoryID(String categoryID);

	
}
