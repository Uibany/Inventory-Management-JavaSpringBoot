package sg.edu.iss.team8ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	@Query("Select c from Customer c where c.customerName = :name")
	public Customer findCusByName(@Param("name") String name);
	

}
