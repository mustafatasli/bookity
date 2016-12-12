package com.bookity.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookity.domain.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("FROM Book")
  List<Book> listBooks();
  List<Book> findBookByCategory(String category);

}