package net.rest.domain.dto;

import net.rest.domain.Customer;

public class OrderDTO {
  
  private long orderNumber;
  private Customer customer;
  
  public long getOrderNumber() {
      return orderNumber;
  }
  public void setOrderNumber(long orderNumber) {
      this.orderNumber = orderNumber;
  }
  public Customer getCustomer() {
      return customer;
  }
  public void setCustomer(Customer customer) {
      this.customer = customer;
  }
}
