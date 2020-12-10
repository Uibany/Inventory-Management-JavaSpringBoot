package sg.edu.iss.team8ca.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Inventory {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String productId;
	private String name;
	private String description;
	private double originalPrice;
	private double wholesalePrice;
	private double retailPrice;
	private double partnerPrice;
	private int stockQty;
	private int reorderLevel;
	private int minimumOrder;
	private String colour;
	private String dimension;
	
	@ManyToOne
	private Subcategory subcategory;
	
	@ManyToOne
	private Brand brand;
}