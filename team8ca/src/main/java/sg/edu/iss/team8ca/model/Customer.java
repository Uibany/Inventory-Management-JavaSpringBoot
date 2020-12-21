package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	@NotEmpty(message = "*Please enter your username")
	private String customerName;
	@NotEmpty(message = "*Please enter your contact number")
	private String contactNo;
	@NotEmpty(message = "*Please enter your email")
	private String email;
	@NotEmpty(message = "*Please enter your address")
	private String address;
	
	public Customer(List<InvUsage> invUsage, @NotEmpty(message = "*Please enter your username") String customerName,
			@NotEmpty(message = "*Please enter your contact number") String contactNo,
			@NotEmpty(message = "*Please enter your email") String email,
			@NotEmpty(message = "*Please enter your address") String address) {
		super();
		this.invUsage = invUsage;
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
	}

	public Customer(@NotEmpty(message = "*Please enter your username") String customerName,
			@NotEmpty(message = "*Please enter your contact number") String contactNo,
			@NotEmpty(message = "*Please enter your email") String email,
			@NotEmpty(message = "*Please enter your address") String address) {
		super();
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
	}
	
	

}
