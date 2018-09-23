package net.rest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.rest.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

  
      public List<Customer> findAll();
    
      public List<Customer> findByLastName(String lastName);
      
      public Customer save(Customer customer);
      
      public Customer findById(long id);
  
//    List<Article> findByTitle(String title);
//    List<Article> findDistinctByCategory(String category);
//    List<Article> findByTitleAndCategory(String title, String category);
//
//    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);
	
	//Paging
}
