
package sg.edu.iss.team8ca.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class InvUsage {

	public InvUsage(Date usageDate, List<UsageDetails> usageDetails) {
		super();
		this.usageDate = usageDate;
		this.usageDetails = usageDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date usageDate;

	@OneToMany(mappedBy = "invUsage")
	private List<UsageDetails> usageDetails;

}
