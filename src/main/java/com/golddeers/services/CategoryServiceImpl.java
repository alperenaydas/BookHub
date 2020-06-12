package com.golddeers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.commands.CategoryForm;
import com.golddeers.converters.CatFormToCat;
import com.golddeers.model.Category;
import com.golddeers.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;
	private CatFormToCat catFormToCat;
	
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository catRepository, CatFormToCat catFormToCat) {

		this.categoryRepository = catRepository;
		this.catFormToCat= catFormToCat;
	}
	
	@Override
	public List<Category> listAll() {
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);
		
		return categories;
	}

	@Override
	public Category getByName(String name) {
		return categoryRepository.findById(name).orElse(null);
	}

	@Override
	public Category saveOrUpdate(Category category) {
		categoryRepository.save(category);
		return category;
	}

	@Override
	public void delete(Long long1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String name) {
		categoryRepository.deleteById(name);
	}

	@Override
	public Category saveOrUpdateCategoryForm(CategoryForm categoryForm) {
		Category savedCategory = saveOrUpdate(catFormToCat.convert(categoryForm));
		System.out.println("Saved Category: " + savedCategory.getName());
		return savedCategory;
	}
}
