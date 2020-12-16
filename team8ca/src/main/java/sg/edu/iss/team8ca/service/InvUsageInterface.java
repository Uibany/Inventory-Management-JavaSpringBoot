
package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;

public interface InvUsageInterface {

//	record creation
	public void addUsage (InvUsage invUsage);
	public void addUsageDetails (UsageDetails usageDetails);
	public void addTransHistory (TransHistory transHistory);
	
//	record deletion
	public void deleteUsage (InvUsage invUsage);
	public void deleteUsageDetails (UsageDetails usageDetails);
	public void deleteTransHistory(TransHistory transHistory);
	
//	display record
	public List<InvUsage> listAllUsageRecord();
	public List<UsageDetails> listDetailsForUdId(Long id);
	public List<UsageDetails> listUsageForInvId(Long id, LocalDate startDate, LocalDate endDate);
	public List<Inventory> listAllInventory();
	public List<Inventory> invSearch(String keyword);
	public Inventory findInvById(Long id);
	public InvUsage findUsageById(Long id);
	public UsageDetails findUsageDetailsById(Long id);
	
//	update record
	public void updateUsageDetails(UsageDetails usageDetails);
	public void reduceInventory(int quantity, Inventory inventory);

}
