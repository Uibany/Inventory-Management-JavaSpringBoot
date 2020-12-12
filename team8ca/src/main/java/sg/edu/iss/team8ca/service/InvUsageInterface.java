
package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.UsageDetails;

public interface InvUsageInterface {

	public InvUsage findInvUsage(long id);

	public void deleteInvUsage(long id);

	public List<InvUsage> listInvUsageById(long productId);

	public List<InvUsage> listInvUsageByDate(long productId, Date startDate, Date endDate);

	public void saveUsage(InvUsage invUsage);

	public void saveUsageDetails(UsageDetails usageDetails);

}
