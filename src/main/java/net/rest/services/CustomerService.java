package net.rest.services;

import java.util.List;
import net.rest.domain.Customer;

public interface CustomerService {

  public List<Customer> findAll();
  
  public List<Customer> findByLastName(String lastName);
  
  public Customer save(Customer customer);
  
  public Customer findById(long customerId);
}
