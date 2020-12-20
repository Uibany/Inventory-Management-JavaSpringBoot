package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@OneToMany(mappedBy="customer")
	private List<InvUsage> invUsage;
	
	
	@NotNull
	private String customerName;
	@NotNull
	private String contactNo;
	private String email;
	@NotNull
	private String address;
	@NotNull
	private int postalCode;
	public Customer(@NotNull String customerName, String contactNo, String email, String address, int postalCode) {
		super();
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
		this.postalCode = postalCode;
	}
}
