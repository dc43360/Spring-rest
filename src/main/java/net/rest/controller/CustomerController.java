package net.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import net.rest.domain.Customer;
import net.rest.services.CustomerService;

@RestController
@RequestMapping("/api/1.0/customers")
public class CustomerController {

  @Autowired
  CustomerService customerService;


  @SuppressWarnings("rawtypes")
  @GetMapping(value = "/{customerId}", produces = "application/json")
  public ResponseEntity getCustomerById(@PathVariable long customerId) {
    Customer customer = customerService.findById(customerId);
    if (customer == null) {
      return new ResponseEntity<>("No customer with id " + customerId + ".", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @SuppressWarnings("rawtypes")
  @GetMapping(value = "/{customerId}/orders", produces = "application/json")
  public ResponseEntity getCustomerOrders(@PathVariable long customerId) {
    Customer customer = customerService.findById(customerId);
    if (customer == null) {
      return new ResponseEntity<>("No orders for customer with id " + customerId + " in database.",
          HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(customer.getOrders(), HttpStatus.OK);
  }



  @SuppressWarnings("rawtypes")
  @GetMapping(value = "", produces = "application/json", params = "customerLastName")
  public ResponseEntity getCustomerByLastName(@RequestParam String customerLastName) {
    List<Customer> customer = customerService.findByLastName(customerLastName);
    if (customer == null) {
      return new ResponseEntity<>("No customer with lastname " + customerLastName + " in database.",
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @SuppressWarnings("rawtypes")
  @GetMapping(value = "")
  public ResponseEntity findAll() {
    List<Customer> customers = customerService.findAll();
    if (customers.isEmpty()) {
      return new ResponseEntity<>("No customers.", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }



  /**
   * @param customer
   * 
   *        201 Crated - resource created 
   *        200 OK - method processing 
   *        204 NO Content - nothing to
   *        return 
   *        400 Bad request
   * 
   */
  @PostMapping(value = "", produces = "application/json")
  public ResponseEntity<Void> createCustomer(@RequestBody @Valid Customer customer) {
    Customer newCustomer = customerService.save(customer);
    if (newCustomer == null)
      return ResponseEntity.noContent().build();

     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(newCustomer.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  
  @RequestMapping(path = "/download", method = RequestMethod.GET)
  public ResponseEntity<Resource> download(String param) throws IOException {

    File file = new ClassPathResource("application.properties").getFile();
    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    headers.add("Pragma", "no-cache"); // HTTP 1.0 same as Cache control prevent client from caching
    headers.add("Expires", "0");

    return ResponseEntity.ok().headers(headers).contentLength(file.length())
        .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
  }



  // @RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
  // public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
  // try {
  // service.createUser(user);
  // return new ResponseEntity<>(HttpStatus.OK);
  // } catch (WrongParameters p) {
  // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  // } catch (InternalServerError u) {
  // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }
  //
  // @RequestMapping(value="", method=RequestMethod.PUT, produces="application/json" )
  // public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
  // try {
  // service.updateUser(user);
  // return new ResponseEntity<>(HttpStatus.OK);
  // } catch (WrongParameters p) {
  // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  // } catch (InternalServerError u) {
  // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }
  //
  // @RequestMapping(value="{username}", method=RequestMethod.DELETE, produces="application/json" )
  // public ResponseEntity<UserDTO> delete(@PathVariable String username) {
  // try {
  // service.deleteUser(username);
  // return new ResponseEntity<>(HttpStatus.OK);
  // } catch (WrongParameters p) {
  // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  // } catch (InternalServerError u) {
  // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

}
