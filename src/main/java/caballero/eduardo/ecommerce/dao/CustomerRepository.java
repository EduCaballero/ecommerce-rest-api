package caballero.eduardo.ecommerce.dao;

import caballero.eduardo.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(exported = false) // setting this to false means Spring Data REST will **NOT** publicly expose this repository in the API @ http://localhost:8080/api
//@RepositoryRestResource(collectionResourceRel = "customer", path = "countries")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
