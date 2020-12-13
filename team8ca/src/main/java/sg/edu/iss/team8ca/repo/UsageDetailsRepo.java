
package sg.edu.iss.team8ca.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.UsageDetails;

public interface UsageDetailsRepo extends JpaRepository<UsageDetails, Long> {
	
	@Query("Select ud from UsageDetails ud where ud.id =: id")
	List<UsageDetails> findUdById(@Param("id") Long id);
	
//	@Query("Select usage from TransHistory)
	List<UsageDetails> listUsageForInvId(Long id, LocalDate startDate, LocalDate endDate);
	
	

}
