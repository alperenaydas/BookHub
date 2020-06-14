package com.golddeers.services;

import com.golddeers.model.Book;
import com.golddeers.model.Cart;

import java.math.BigDecimal;
import java.util.List;


public interface CartService {
    List<Cart> listAll();
    void removeBook(Book book,Long id);
    //Map<Book, Integer> getBooksInCart();
    Cart getByBookId(Long bookid, String username);
    BigDecimal getTotal();
    void addBook(Book book);
    Cart saveOrUpdate(Cart cart);
    void delete(Long fakeid);
    List<Cart> findByUsernameContaining(Object username);

}
