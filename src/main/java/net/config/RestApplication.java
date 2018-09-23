package net.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration 
@Configuration
@SpringBootApplication(scanBasePackages = {"net.rest"})
@EnableJpaRepositories(basePackages= {"net.rest.repositories"})
@EntityScan( basePackages = {"net.rest.domain"} )
@Import(JavaMelodyConfiguration.class)
public class RestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
  
  @Bean
  public ModelMapper modelMapper() {
      return new ModelMapper();
  }
}
