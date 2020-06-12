package com.golddeers.services;

import java.util.List;

import com.golddeers.commands.CategoryForm;
import com.golddeers.model.Category;

public interface CategoryService {

	List<Category> listAll();

	Category getByName(String name);

	Category saveOrUpdate(Category category);

	void delete(Long long1);
	
	void delete(String name);

	Category saveOrUpdateCategoryForm(CategoryForm categoryForm);
}
