package com.golddeers.services;

import com.golddeers.model.Book;
import com.golddeers.model.Cart;

import java.math.BigDecimal;


public interface CartService {

    void removeBook(Book book,Long id);
    //Map<Book, Integer> getBooksInCart();
    BigDecimal getTotal();
    void addBook(Book book);
    Cart saveOrUpdate(Cart cart);

}
