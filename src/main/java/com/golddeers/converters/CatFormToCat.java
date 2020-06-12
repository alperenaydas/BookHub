package com.golddeers.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.golddeers.commands.CategoryForm;
import com.golddeers.model.Category;

@Component
public class CatFormToCat implements Converter<CategoryForm, Category>{

	@Override
	public Category convert(CategoryForm categoryForm) {
		Category cat = new Category();
		if (categoryForm.getName() != null && !StringUtils.isEmpty(categoryForm.getName())) {
			cat.setName(new String(categoryForm.getName()));
		}
		cat.setName(categoryForm.getName());
		return cat;
	}

}
