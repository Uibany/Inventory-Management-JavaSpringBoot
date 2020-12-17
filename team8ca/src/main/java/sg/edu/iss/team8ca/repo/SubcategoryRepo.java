package sg.edu.iss.team8ca.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Subcategory;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
		
	@Query("Select s from Subcategory s where s.subcategoryName = :name")
	 List<Subcategory> findSubcatByName(@Param("name") String name);

}
