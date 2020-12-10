package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;

import sg.edu.iss.team8ca.model.InvUsage;


public interface InvUsageInterface {
	public void addInvUsage(InvUsageInterface invUsage);
	public List<InvUsageInterface> listInvUsageById(long id);
	public List<InvUsageInterface> listInvUsageByDate(long id, Date startDate, Date endDate);
	public InvUsage findInvUsage(long id);

}
