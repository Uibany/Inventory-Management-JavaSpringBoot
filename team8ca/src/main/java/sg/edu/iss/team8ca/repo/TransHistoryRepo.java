
package sg.edu.iss.team8ca.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;

public interface TransHistoryRepo extends JpaRepository<TransHistory, Long> {
	@Query("Select th from TransHistory th where th.inventory.id = :id AND (th.date BETWEEN :startDate AND :endDate)")
	public List<TransHistory> listTransHisForInvId(@Param("id")Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
