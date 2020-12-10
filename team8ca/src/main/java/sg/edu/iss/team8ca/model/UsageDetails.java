package sg.edu.iss.team8ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.jboss.jandex.TypeTarget.Usage;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class UsageDetails {
	
	public UsageDetails(Inventory inventory, InvUsage invUsage) {
		super();
		this.inventory = inventory;
		this.invUsage = invUsage;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Inventory inventory;
	
	@ManyToOne
	private InvUsage invUsage;
}
