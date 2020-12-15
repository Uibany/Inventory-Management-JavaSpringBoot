
package sg.edu.iss.team8ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Inventory;


public interface InventoryRepo extends JpaRepository<Inventory, Long> {
	
	@Query("Select inv from Inventory inv where inv.id = :id")
	public Inventory findInvById(@Param("id") long id);
	
	@Query("Select inv from Inventory inv where inv.productName = :name")
	public Inventory findInvByName(@Param("name") String name);
}
