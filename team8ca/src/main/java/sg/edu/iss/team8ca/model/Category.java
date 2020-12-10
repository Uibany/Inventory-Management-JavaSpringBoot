package sg.edu.iss.team8ca.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String categoryId;
	private String categoryName;
	
	@OneToMany(mappedBy="category")
	private List<Subcategory> subcategory;
}
