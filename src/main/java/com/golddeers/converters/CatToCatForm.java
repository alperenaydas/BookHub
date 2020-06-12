package com.golddeers.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.golddeers.commands.CategoryForm;
import com.golddeers.model.Category;

@Component
public class CatToCatForm implements Converter<Category, CategoryForm>{

	@Override
	public CategoryForm convert(Category category) {
		CategoryForm categoryForm = new CategoryForm();
		categoryForm.setName(category.getName());
		return categoryForm;	
		}

}
