package sg.edu.iss.team8ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private TransType transType;
	
	@ManyToOne
	private Inventory inventory;
	
	@ManyToOne
	private Users users;

	public TransHistory(TransType transType, Inventory inventory, Users users) {
		super();
		this.transType = transType;
		this.inventory = inventory;
		this.users = users;
	}
	
	
}
