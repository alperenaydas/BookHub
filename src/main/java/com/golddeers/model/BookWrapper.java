package com.golddeers.model;

import java.util.Set;

public class BookWrapper {

	private Set<Book> bookList;

	public BookWrapper(Set<Book> bookList) {

		this.bookList = bookList;
	}

	public Set<Book> getBookList() {
		return bookList;
	}

	public void setBookList(Set<Book> bookList) {
		this.bookList = bookList;
	}
}
