
package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	@NotNull
	@Size(min=2, max=100)
	private String productName;
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

//	@OneToMany(mappedBy = "transHistory")
//	private List<TransHistory> transHistory;

}
