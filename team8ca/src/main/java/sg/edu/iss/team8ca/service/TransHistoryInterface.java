package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;

public interface TransHistoryInterface {
	public List<TransHistory> listAllTransHis();
	public List<TransHistory> listTransHisForId(long id);
	public List<TransHistory> listTransHisForDate(long id, LocalDate startDate, LocalDate endDate);
	public Inventory findInvById(long id);
	
}
