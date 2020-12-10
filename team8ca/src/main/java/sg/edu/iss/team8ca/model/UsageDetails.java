package sg.edu.iss.team8ca.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class UsageDetails {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Inventory inventory;
	
	@ManyToOne
	private Usage usage;
}
