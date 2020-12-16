
package sg.edu.iss.team8ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Inventory;


public interface InventoryRepo extends JpaRepository<Inventory, Long> {
	
	@Query("SELECT inv FROM Inventory inv WHERE inv.id = :id")
	public Inventory findInvById(@Param("id") long id);
	
	@Query("SELECT inv FROM Inventory inv WHERE inv.productName = :name")
	public Inventory findInvByName(@Param("name") String name);
	
	@Query("SELECT i FROM Inventory i WHERE i.productName LIKE %?1%"
			+ "OR i.description LIKE %?1%"
			+ "OR i.colour LIKE %?1%"
			+ "OR i.dimension LIKE %?1%"
			+ "OR i.brand.brandName LIKE %?1%"
			+ "OR i.brand.manufacturerName LIKE %?1%")
	public List<Inventory> invSearch(String keyword);
