package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;

import sg.edu.iss.team8ca.model.InvUsage;

public interface InvUsageInterface {
	
	public InvUsage findInvUsage(long id);
	public void deleteInvUsage(long id);
	public List<InvUsage> listInvUsageById(String productId);
	public List<InvUsage> listInvUsageByDate(String productId, Date startDate, Date endDate);
	

}
