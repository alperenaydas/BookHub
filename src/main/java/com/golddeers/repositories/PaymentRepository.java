package com.golddeers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.golddeers.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

}