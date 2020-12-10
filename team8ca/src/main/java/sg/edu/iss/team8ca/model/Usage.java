package sg.edu.iss.team8ca.model;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Usage {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	private Date usageDate; 
	
	@OneToMany(mappedBy = "usage")
	private List<UsageDetails> usageDetails;
	
	
}
