package com.golddeers.services;

import com.golddeers.model.Book;
import com.golddeers.model.Cart;
import com.golddeers.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {


    private CartRepository cartRepository;
    //private BookRepository bookRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) { this.cartRepository = cartRepository;}
    //@Autowired
    //public CartServiceImpl(BookRepository bookRepository) { this.bookRepository = bookRepository;}


    @Override
    public void removeBook(Book book, Long id) {
        cartRepository.deleteById(id);}

    @Override
    public BigDecimal getTotal() {
        System.out.println("sa");;
    return BigDecimal.valueOf(5);}
    @Override
    public void addBook(Book book) {
        System.out.println("as");
    }
    @Override
    public Cart saveOrUpdate(Cart cart){
        cartRepository.save(cart);
        return cart;

    }
}

