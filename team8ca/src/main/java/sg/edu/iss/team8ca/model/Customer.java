package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
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
	
	@NotBlank
	private String customerName;
	@NotNull
	private String contactNo;
	@NotNull	
	private String email;
	@NotNull
	private String address;
	@NotNull
	private int postalCode;
	public Customer(@NotNull String customerName,@NotNull String contactNo,@NotNull String email,@NotNull String address,@NotNull int postalCode) {
		super();
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
		this.postalCode = postalCode;
	}
}
