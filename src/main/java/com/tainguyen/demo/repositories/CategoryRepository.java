package com.tainguyen.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tainguyen.demo.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{
	
}
