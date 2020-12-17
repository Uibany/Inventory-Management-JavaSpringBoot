package sg.edu.iss.team8ca.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

	@Query("Select s from Supplier s where s.companyName = :name")
	 List<Supplier> findSupplierByName(@Param("name") String name);
}
