package sg.edu.iss.team8ca.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fixset")
public class Fixset {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String fixsetName;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "pk.fixset")
    @Valid
    private List<FixsetItem> fixsetItems = new ArrayList<>();
	
	@Transient
    public int getNumberOfItems() {
        return this.fixsetItems.size();
    }

	public Fixset(String fixsetName) {
		this.fixsetName = fixsetName;
	}
	
	
}
