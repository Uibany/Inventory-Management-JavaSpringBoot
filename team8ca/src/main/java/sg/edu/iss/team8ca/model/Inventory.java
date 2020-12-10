package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.OneToMany;

public class Inventory {
	
	
	
	
	@OneToMany (mappedBy = "inventory")
	private List<UsageDetails> usagedetails;

}
