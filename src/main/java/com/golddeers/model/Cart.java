package com.golddeers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long fakeid;
	private Long bookid;
	private String username;
	private Double totalprice = 0.00;
	private boolean sold = false;

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Cart(Long bookid, String username) {
		this.bookid = bookid;
		this.username = username;
	}

	public Cart() {

	}

	public Long getBookid() {
		return bookid;
	}

	public void setbookid(Long bookid) {
		this.bookid = bookid;
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
