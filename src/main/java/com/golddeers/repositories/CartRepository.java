package com.golddeers.repositories;

import com.golddeers.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findByUsernameIgnoreCaseContaining(Object username);
}

