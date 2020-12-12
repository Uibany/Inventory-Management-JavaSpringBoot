package sg.edu.iss.team8ca.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String categoryName;
	
	@OneToMany(mappedBy="category")
	private List<Subcategory> subcategory;

	public Category(String categoryName, List<Subcategory> subcategory) {
		super();
		this.categoryName = categoryName;
		this.subcategory = subcategory;
	}
	
}
