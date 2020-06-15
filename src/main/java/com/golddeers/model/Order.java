package com.golddeers.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long fakeid;
	private String username;
	private boolean cargoStatus;
	private LocalDate date;
	private Double totalprice = 0.00;

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isCargoStatus() {
		return cargoStatus;
	}

	public void setCargoStatus(boolean cargoStatus) {
		this.cargoStatus = cargoStatus;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Order(String username, boolean cargoStatus, LocalDate date) {
		this.username = username;
		this.cargoStatus = cargoStatus;
		this.date = date;
	}

	public Order() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getFakeid() {
		return fakeid;
	}

	public void setFakeid(Long fakeid) {
		this.fakeid = fakeid;
	}

}
