package com.golddeers.repositories;

import com.golddeers.model.Cart;
import org.springframework.data.repository.CrudRepository;


public interface CartRepository extends CrudRepository<Cart, Long> {
}
