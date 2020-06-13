package com.golddeers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golddeers.model.Payment;
import com.golddeers.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Payment saveOrUpdate(Payment payment) {
		paymentRepository.save(payment);
		return payment;
	}

	@Override
	public Payment findById(Long id) {
		return paymentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Payment> listAll() {
		List<Payment> payments = new ArrayList<>();
		paymentRepository.findAll().forEach(payments::add);

		return payments;
	}
}
