package com.golddeers.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.golddeers.commands.BookForm;
import com.golddeers.commands.CategoryForm;
import com.golddeers.converters.BookToBookForm;
import com.golddeers.model.Book;
import com.golddeers.model.Category;
import com.golddeers.services.BookService;
import com.golddeers.services.CategoryService;

@Controller
public class BookController {

	private BookService bookService;
	private CategoryService categoryService;

	private BookToBookForm bookToBookForm;

	@Autowired
	public void setBookToBookForm(BookToBookForm bookToBookForm) {
		this.bookToBookForm = bookToBookForm;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping({ "/book/list", "/book" })
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.listAll());
		if (Session.online.containsValue("registered") || Session.online.containsValue("Registered")) {
			if (Session.online.isEmpty() == false) {

				if (Session.online.containsValue("admin")) {

					model.addAttribute("admin", true);
					model.addAttribute("user_type", "admin");
				} else {
					model.addAttribute("admin", false);
					model.addAttribute("user_type", "registered");
				}
				model.addAttribute("usersOnline", Session.online);
				model.addAttribute("username", Session.online.keySet().toArray()[0]);

			}
			return "winter/index";
		} else if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
			if (Session.online.isEmpty() == false) {

				if (Session.online.containsValue("admin")) {

					model.addAttribute("admin", true);
					model.addAttribute("user_type", "admin");
				} else {
					model.addAttribute("admin", false);
					model.addAttribute("user_type", "registered");
				}
				model.addAttribute("usersOnline", Session.online);
				model.addAttribute("username", Session.online.keySet().toArray()[0]);

			}
			return "book/list";
		} else {
			if (Session.online.isEmpty() == false) {

				if (Session.online.containsValue("admin")) {

					model.addAttribute("admin", true);
					model.addAttribute("user_type", "admin");
				} else {
					model.addAttribute("admin", false);
					model.addAttribute("user_type", "registered");
				}
				model.addAttribute("usersOnline", Session.online);
				model.addAttribute("username", Session.online.keySet().toArray()[0]);

			}
			return "general/login";
		}
	}

	/*
	 * @RequestMapping("/book/show/{id}") public String getBook(@PathVariable String
	 * id, Model model) { model.addAttribute("book",
	 * bookService.getById(Long.valueOf(id))); return "book/show"; }
	 */

	@RequestMapping("/book/details/{id}")
	public String getBookDetails(@PathVariable String id,
			@ModelAttribute("mapping1Form") final Object mapping1FormObject, final BindingResult mapping1BindingResult,
			final Model model) {
		model.addAttribute("book", bookService.getById(Long.valueOf(id)));
		model.addAttribute("admin", true);
		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");
			} else {
				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}
			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		return "book/single-product";
	}

	@RequestMapping("book/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Book book = bookService.getById(Long.valueOf(id));
		BookForm bookForm = bookToBookForm.convert(book);

		model.addAttribute("bookForm", bookForm);
		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");
			} else {
				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}
			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		return "book/bookform";
	}

	@RequestMapping("/book/new")
	public String newBook(Model model) {
		model.addAttribute("bookForm", new BookForm());
		if (Session.online.isEmpty() == false) {

			if (Session.online.containsValue("admin")) {

				model.addAttribute("admin", true);
				model.addAttribute("user_type", "admin");
			} else {
				model.addAttribute("admin", false);
				model.addAttribute("user_type", "registered");
			}
			model.addAttribute("usersOnline", Session.online);
			model.addAttribute("username", Session.online.keySet().toArray()[0]);

		}
		return "book/bookform";
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public String saveOrUpdateBook(@Valid BookForm bookForm, BindingResult bindingResult,
			@ModelAttribute("admin") final Object mapping1, final Model model,
			final RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "book/bookform";
		}

		String cat = bookForm.getGenre();
		String[] catNames = cat.split(" ");
		String newGenre = "";
		for (String categoryCandidate : catNames) {
			String name = categoryCandidate.substring(0, 1).toUpperCase()
					+ categoryCandidate.substring(1).toLowerCase();
			Category fetched = categoryService.getByName(name);
			if (fetched == null) { // Add this as new category
				CategoryForm newCatForm = new CategoryForm();
				newCatForm.setName(name);
				Category savedCategory = categoryService.saveOrUpdateCategoryForm(newCatForm);
			}
			newGenre += name + " ";

		}
		bookForm.setGenre(newGenre);
		Book savedBook = bookService.saveOrUpdateBookForm(bookForm);

		redirectAttributes.addFlashAttribute("admin", true);
		return "redirect:/book/details/" + savedBook.getId();
	}

	@RequestMapping("/book/delete/{id}")
	public String delete(@PathVariable String id) {
		bookService.delete(Long.valueOf(id));
		return "redirect:/book/list";
	}
}
