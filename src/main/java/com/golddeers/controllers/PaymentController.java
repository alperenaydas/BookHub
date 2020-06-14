package com.golddeers.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golddeers.model.Payment;
import com.golddeers.repositories.PaymentRepository;
import com.golddeers.services.PaymentService;

@Controller
public class PaymentController {
	private PaymentRepository paymentRepository;
	private PaymentService paymentService;

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Autowired
	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@RequestMapping("/payment/confirm/{id}")
	public String confirmPayment(@PathVariable String id) {
		long Id = Long.valueOf(id);
		Payment paymentFetched = paymentService.findById(Id);
		paymentFetched.setTime(LocalDateTime.now());
		paymentFetched.setConfirmed(true);
		paymentService.saveOrUpdate(paymentFetched);
		return "redirect:/payments";
	}

	@RequestMapping("/payments")
	public String listPayments(Model model) {
		List<Payment> payments = paymentService.listAll();
		model.addAttribute("payments", payments);
		if (Session.online.containsKey("admin") || Session.online.containsKey("Admin")) {
			model.addAttribute("admin", true);
			return "/payment/list";
		}
		return "winter/index";
	}

	@RequestMapping("/paymentmethods")
	public String listPaymentMethods(Model model) {
		HashMap<String, String> methods = new HashMap();
		methods.put("PayPal", "https://www.paypal.com/");
		methods.put("Yapıkredi", "https://www.yapikredi.com.tr/");
		methods.put("Cash", "");

		if (Session.online.containsValue("registered")) {
			model.addAttribute("registered", true);
			model.addAttribute("paymentMethods", methods);
			return "/payment/methods";
		}
		return "winter/index";
	}
}
