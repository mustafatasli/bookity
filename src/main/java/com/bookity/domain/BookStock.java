package com.bookity.domain;

import java.math.BigDecimal;


public class BookStock {
	private int stock;
	private BigDecimal price;
	private String isbn;

	public BookStock(String isbn, int stock, BigDecimal price) {
		this.isbn = isbn;
		this.stock = stock;
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}