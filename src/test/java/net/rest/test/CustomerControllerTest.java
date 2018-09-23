package net.rest.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import net.config.RestApplication;
import net.rest.controller.CustomerController;
import net.rest.domain.Customer;
import net.rest.services.CustomerService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApplication.class)
public class CustomerControllerTest {

  @InjectMocks
  CustomerController customerController;

  @MockBean
  CustomerService customerService;

  @Autowired
  WebApplicationContext context;


  private MockMvc mockMvc;

  @Before
  public void initTests() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testGetCustomerById() throws Exception {
    // given(this.customerService.findById(1)).willReturn(null);
    this.mockMvc.perform(get("/api/1.0/customers/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testGetOwnerSuccess() throws Exception {
    given(this.customerService.findById(1)).willReturn(mockCustomer());
    this.mockMvc.perform(get("/api/1.0/customers/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  private Customer mockCustomer() {
    Customer r = new Customer();
    r.setDateOfBirth(new Date());
    r.setFirstName("Mio");
    r.setLastName("Mao");
    r.setYears(2);
    r.setId(1l);
    return r;
  }


}
