package sg.edu.iss.team8ca.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Supplier {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String supplierId;
	private String companyName;
	private String contactNo;
	private String email;
	private String address;
	private int postalCode;
	
	@OneToMany(mappedBy="supplier")
	private List<Brand> brand;
}
