package net.rest.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDTO {

  private String firstName;
  
  private String lastName;
 
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date dateOfBirth;
  
  private int years;
  
  public String getFirstName() {
      return firstName;
  }
  
  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  public void setLastName(String lastName) {
      this.lastName = lastName;
  }
  public Date getDateOfBirth() {
      return dateOfBirth;
  }
  public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
  }
  public int getYears() {
      return years;
  }
  public void setYears(int years) {
      this.years = years;
  }
  
}
