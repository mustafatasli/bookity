package com.bookity.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookity.domain.Book;
import com.bookity.domain.repository.BookRepository;


@Service
public class BookService {

	private BookRepository repository;

	@Autowired
	public BookService(BookRepository repository) {
		this.repository = repository;
	}

	public List<Book> listAll() {
		return repository.listBooks();
	}

	public List<Book> findByCategory(String category) {
		return repository.findBookByCategory(category);
	}

	public Book save(Book book) {
		return repository.save(book);
	}

	public Book get(Long id) {
		return repository.findOne(id);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}