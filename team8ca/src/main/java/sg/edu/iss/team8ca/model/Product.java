package sg.edu.iss.team8ca.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@NotNull(message = "Product name is required.")
    @Basic(optional = false)
	private String name;
	
	private Integer quantity;
	
	public Product(@NotNull(message = "Product name is required.") String name, Integer quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

}