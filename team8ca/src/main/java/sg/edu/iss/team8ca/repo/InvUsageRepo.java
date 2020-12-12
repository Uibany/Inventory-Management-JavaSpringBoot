
package sg.edu.iss.team8ca.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.team8ca.model.InvUsage;

public interface InvUsageRepo extends JpaRepository<InvUsage, Long> { 
	// @Query
//	public List<InvUsage> listInvUsageByDate(long productId, Date startDate, Date endDate);

}
