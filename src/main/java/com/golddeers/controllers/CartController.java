package com.golddeers.controllers;

import com.golddeers.model.Cart;
import com.golddeers.repositories.CartRepository;
import com.golddeers.services.BookService;
import com.golddeers.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CartController {

    private CartRepository cartRepository;
    private BookService bookService;
    private CartService cartService;


    @Autowired
    public void  setCartService(CartService cartService) {this.cartService=cartService;}
    @Autowired
    public void  setBookService(BookService bookService) {this.bookService=bookService;}

    @RequestMapping("/addBookToCart/{id}")
    public String addBookToCart(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.getById(Long.valueOf(id)));
        String username= (String)Session.online.keySet().toArray()[0];
        //if(model.addAttribute("cart")==null){
         //   List<Book> cart = new ArrayList<Book>();
         //   cart.add(bookService.getById(Long.valueOf(id)));
        System.out.println(id);
        System.out.println(username);


        cartService.saveOrUpdate(new Cart(Long.valueOf(id), username));  //add book to cart by id in controller (doesnt work for now)
        return "book/single-product";

       // System.out.println(id);

        //bookService.getById(Long.valueOf(id)).filter(cartService::addBook);





    }
    @RequestMapping("/cart") //we have errors when we write like this: ("/cart")
    public String listBooks(Model model, Session session){
        if (Session.online.containsValue("admin") || Session.online.containsValue("Admin")) {
            return "/winter/index";
        } else {
            model.addAttribute("carts", cartService.listAll());
            model.addAttribute("sess", Session.online);
            System.out.println(cartService.listAll());
            return "/winter/cart";
        }



    }
    @RequestMapping("/cart/delete/{id}")
    public String delete(@PathVariable String id) {
        cartService.delete(Long.valueOf(id));
        return "redirect:/cart";
    }




}
