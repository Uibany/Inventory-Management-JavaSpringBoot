package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String brandName;
	private String manufacturerName;
	
	@ManyToOne
	private Supplier supplier;

	public Brand(String brandName, String manufacturerName, Supplier supplier) {
		super();
		this.brandName = brandName;
		this.manufacturerName = manufacturerName;
		this.supplier = supplier;
	}
	
	
	
}
