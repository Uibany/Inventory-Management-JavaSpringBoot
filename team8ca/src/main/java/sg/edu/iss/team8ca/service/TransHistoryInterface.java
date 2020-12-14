package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;

public interface TransHistoryInterface {
	public List<TransHistory> listTransHisForInvId(Long id, LocalDate startDate, LocalDate endDate);
}
