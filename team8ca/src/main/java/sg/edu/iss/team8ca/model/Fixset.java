package sg.edu.iss.team8ca.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="fixset")
public class Fixset {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fixsetId;
	
	private String fixsetName;
 
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
 
    private Integer quantity;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

	public Fixset(String fixsetName, LocalDate dateCreated, Integer quantity, Product product) {
		super();
		this.fixsetName = fixsetName;
		this.dateCreated = dateCreated;
		this.quantity = quantity;
		this.product = product;
	}

}
