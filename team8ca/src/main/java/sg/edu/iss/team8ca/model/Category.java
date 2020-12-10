package sg.edu.iss.team8ca.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String categoryId;
	private String categoryName;
}
