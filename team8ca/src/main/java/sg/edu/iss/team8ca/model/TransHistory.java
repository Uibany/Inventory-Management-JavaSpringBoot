
package sg.edu.iss.team8ca.model;

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
public class TransHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private TransType transType;

	@ManyToOne
	private Inventory inventory;

	@ManyToOne
	private User user;

	public TransHistory(TransType transType, Inventory inventory, User user) {
		super();
		this.transType = transType;
		this.inventory = inventory;
		this.user = user;
	}

}
