package sg.edu.iss.team8ca.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team8ca.model.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

}
