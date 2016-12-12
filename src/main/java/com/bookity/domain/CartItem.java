package com.bookity.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class CartItem {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne(targetEntity=Book.class)
  private Book book;

  @ManyToOne
  @JoinColumn(referencedColumnName="id")
  private Cart cart;

  @Column(precision=7, scale=2)
  private BigDecimal unitPice;

  private int quantity = 1;

  @Transient
  private BookStock stock;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public BigDecimal getUnitPrice() {
    return unitPice;
  }

  public void setUnitPrice(BigDecimal price) {
    this.unitPice = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public BookStock getStock() {
    return stock;
  }

  public void setBookStock(BookStock stock) {
    this.stock = stock;
    this.unitPice = stock.getPrice();
  }

}