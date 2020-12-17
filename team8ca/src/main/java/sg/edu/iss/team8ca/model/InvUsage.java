
package sg.edu.iss.team8ca.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class InvUsage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate usageDate;
	private UsageReportStatus usageReportStatus;

	@OneToMany(mappedBy = "invUsage")
	private List<UsageDetails> usageDetails;
	
	@ManyToOne
	private User user;
	
	private String tasks;
	
	public InvUsage(LocalDate usageDate, UsageReportStatus usageReportStatus, User user, String tasks) {
		super();
		this.usageDate = usageDate;
		this.usageReportStatus = usageReportStatus;
		this.user = user;
		this.tasks = tasks;
	}
	
	public InvUsage(LocalDate usageDate, List<UsageDetails> usageDetails, UsageReportStatus usageReportStatus, User user, String tasks) {
		super();
		this.usageDate = usageDate;
		this.usageDetails = usageDetails;
		this.usageReportStatus = usageReportStatus;
		this.user = user;
		this.tasks = tasks;
	}
}
