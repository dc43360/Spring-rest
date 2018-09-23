package net.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.rest.common.util.doman.AbstractId;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractId{

    @Column(name="order_number")
	private long orderNumber;
	
	@ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
	@JsonBackReference
	private Customer customer;
	
	
	  public Order() {
	    super();
	  }
	
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
