package com.bookity.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name="customer_id")
  private Customer customer;

  @OneToMany(targetEntity=CartItem.class, mappedBy="cart", cascade=CascadeType.ALL)
  private List<CartItem> items;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public void addItem(CartItem item) {
    this.items.add(item);
  }

}