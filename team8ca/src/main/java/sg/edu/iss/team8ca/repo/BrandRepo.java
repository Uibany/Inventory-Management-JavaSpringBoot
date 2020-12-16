package sg.edu.iss.team8ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
	@Query("Select b from Brand b where b.brandName = :name")
	 List<Brand> findBrandByName(@Param("name") String name);
}
