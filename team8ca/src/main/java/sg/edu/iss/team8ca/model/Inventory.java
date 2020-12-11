
package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@OneToMany(mappedBy = "inventory")
	private List<UsageDetails> usageDetails;

	@OneToMany(mappedBy = "transHistory")
	private List<TransHistory> transHistory;

}
