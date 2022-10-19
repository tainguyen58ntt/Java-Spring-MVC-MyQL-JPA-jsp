package com.tainguyen.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tainguyen.demo.model.Category;
import com.tainguyen.demo.repositories.CategoryRepository;

@Service
public class CategoryService implements ICategoryService{
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = (List<Category>) categoryRepo.findAll();
		return  categories;
	}
	
}
