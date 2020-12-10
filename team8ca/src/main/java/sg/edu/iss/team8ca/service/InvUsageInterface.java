package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;


public interface InvUsageInterface {
	public void addInvUsage(InvUsageInterface invUsage);
	public List<InvUsageInterface> listInvUsageById(Integer id);
	public List<InvUsageInterface> listInvUsageByDate(Integer id, Date startDate, Date endDate);
	public InvUsageInterface findInvUsage(Integer id);

}
