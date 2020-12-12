package sg.edu.iss.team8ca.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Brand {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long brandId;
	private String brandName;
	private String manufacturerName;
	
	@ManyToOne
	private Supplier supplier;
}
