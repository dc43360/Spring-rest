package net.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.rest.common.util.DtoConverter;
import net.rest.common.util.Preconditions;
import net.rest.domain.Customer;
import net.rest.domain.dto.CustomerDTO;
import net.rest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl extends DtoConverter<Customer,CustomerDTO> implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public List<Customer> findByLastName(String lastName) {
    Preconditions.checkNotNullOrEmpty(lastName);
    return customerRepository.findByLastName(lastName);
  }

  @Override
  public Customer findById(long customerId) {
    return customerRepository.findById(customerId);
  }
  
  @Override
  protected CustomerDTO convertToDto(CustomerDTO source, Customer destination) {
    return modelMapper.map(destination, source.getClass());
  }

  @Override
  protected Customer convertToEntity(Customer source, CustomerDTO destination) {
    return modelMapper.map(destination, source.getClass());
  }

  @Override
  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

}
