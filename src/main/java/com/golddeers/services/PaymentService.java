package com.golddeers.services;

import java.util.List;

import com.golddeers.model.Payment;

public interface PaymentService {
	Payment saveOrUpdate(Payment payment);

	Payment findById(Long id);

	List<Payment> listAll();
}