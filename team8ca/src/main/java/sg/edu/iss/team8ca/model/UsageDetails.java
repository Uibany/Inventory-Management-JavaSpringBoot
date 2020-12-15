
package sg.edu.iss.team8ca.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UsageDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Inventory inventory;

	@ManyToOne
	private InvUsage invUsage;
	
	private LocalDate date;
	
	private long quantity;

	public UsageDetails(Inventory inventory, InvUsage invUsage, LocalDate date, long quantity) {
		super();
		this.inventory = inventory;
		this.invUsage = invUsage;
		this.date = date;
		this.quantity = quantity;
	}
}
