package com.golddeers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.golddeers.commands.CategoryForm;
import com.golddeers.converters.CatToCatForm;
import com.golddeers.model.Category;
import com.golddeers.services.CategoryService;

@Controller
public class CategoryController {
	private CategoryService categoryService;
	private CatToCatForm catToCatForm;
	

	@Autowired
	public void setCatToCatForm(CatToCatForm catToCatForm) {
		this.catToCatForm = catToCatForm;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping({ "/category/list", "/category" })
	public String listBooks(Model model) {
		model.addAttribute("categories", categoryService.listAll());
		if (Session.online.containsValue("registered") || Session.online.containsValue("Registered")) {
			return "winter/index";
		} else if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
			return "category/list";
		} else {
			return "general/login";
		}
	}
	
	@RequestMapping("/category/new")
	public String newCategory(Model model) {
		model.addAttribute("categoryForm", new CategoryForm());
		return "category/categoryform";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String saveOrUpdateCategory(@Valid CategoryForm categoryForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "category/categoryform";
		}

		Category savedCategory = categoryService.saveOrUpdateCategoryForm(categoryForm);
		model.addAttribute("categories", categoryService.listAll());
		return "/category/list";
	}
	
	@RequestMapping("/category/delete/{name}")
	public String delete(@PathVariable String name) {
		categoryService.delete(name);
		return "redirect:/category/list";
	}
}
