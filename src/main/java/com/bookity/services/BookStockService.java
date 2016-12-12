package com.bookity.services;

import java.util.List;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookity.domain.Book;
import com.bookity.domain.BookStock;
import com.bookity.domain.CartItem;
import com.bookity.domain.repository.BookRepository;


@Service
public class BookStockService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookStockService.class);

	private BookRepository repository;

	@Autowired
	public BookStockService(BookRepository repository) {
		this.repository = repository;
	}

	public void updateBookStocks(List<CartItem> items) {
		for(CartItem item : items) {
        LOGGER.info("Cartitem " + item.getId());

        BookStock stock = getBookStock(item.getBook());
        item.setBookStock(stock);
      }
	}

	public BookStock getBookStock(Book book) {
		return new BookStock(book.getIsbn(), 1, new BigDecimal("100.0"));
	}

}