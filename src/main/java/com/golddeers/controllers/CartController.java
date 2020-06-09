package com.golddeers.controllers;

import com.golddeers.repositories.CartRepository;
import com.golddeers.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.SchemaOutputResolver;


@Controller
public class CartController {

    private CartRepository cartRepository;
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/addBookToCart/{id}")
    public String addBookToCart(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.getById(Long.valueOf(id)));
        System.out.println(id);
        return "book/single-product";




    }




}
