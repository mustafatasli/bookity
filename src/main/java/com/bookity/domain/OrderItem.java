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
public class OrderItem {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String bookIsbn;
  private String bookTitle;

  private int quantity;

  @Column(precision=7, scale=2)
  private BigDecimal unitPrice;

  @ManyToOne
  @JoinColumn(referencedColumnName="id")
  private Order order;

  @Transient
  private BookStock stock;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String title) {
    this.bookTitle = title;
  }

  public String getBookIsbn() {
    return bookIsbn;
  }

  public void setBookIsbn(String isbn) {
    this.bookIsbn = isbn;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal price) {
    this.unitPrice = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }



}