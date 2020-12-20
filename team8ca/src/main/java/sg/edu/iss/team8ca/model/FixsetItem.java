package sg.edu.iss.team8ca.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FixsetItem {
	
	@EmbeddedId
    @JsonIgnore
    private FixsetItemPK pk;
	
	@Column(nullable = false)
		private Integer quantity;
	

    public FixsetItem(Fixset fixset, Inventory productName, Integer quantity) {
        pk = new FixsetItemPK();
        pk.setFixset(fixset);
        pk.setProductName(productName);
        this.quantity = quantity;
    }

    @Transient
    public Inventory getProduct() {
        return this.pk.getProductName();
    }

}
