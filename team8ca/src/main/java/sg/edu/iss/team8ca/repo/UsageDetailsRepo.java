package sg.edu.iss.team8ca.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.UsageDetails;

public interface UsageDetailsRepo extends JpaRepository<UsageDetails, Long> {
	
	@Query("Select ud from UsageDetails ud where ud.invUsage.id = :id")
	public List<UsageDetails> findUdById(@Param("id") Long id);
	
	@Query("Select ud from UsageDetails ud where ud.inventory.id = :id AND (ud.invUsage.usageDate BETWEEN :startDate AND :endDate)")
	public List<UsageDetails> listUsageForInvId(@Param("id")Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);	
}
