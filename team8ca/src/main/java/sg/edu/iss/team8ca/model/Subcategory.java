package sg.edu.iss.team8ca.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subcategory {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long subcategoryId;
	private String subcategoryName;
	private String subcategoryType;
	
	@ManyToOne
	private Category category;
	
}
