package sg.edu.iss.team8ca.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team8ca.model.Subcategory;
import sg.edu.iss.team8ca.model.User;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
	Subcategory findBySubcategoryName(String name);

}
