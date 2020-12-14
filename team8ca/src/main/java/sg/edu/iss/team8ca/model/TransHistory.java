
package sg.edu.iss.team8ca.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TransHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private TransType transType;
	private LocalDate date;
	@ManyToOne
	private Inventory inventory;
	private int quantity;
	@ManyToOne
	private User user;

	public TransHistory(TransType transType, LocalDate date, Integer quantity, Inventory inventory, User user) {
		super();
		this.transType = transType;
		this.quantity = quantity;
		this.inventory = inventory;
		this.user = user;
	}

}
