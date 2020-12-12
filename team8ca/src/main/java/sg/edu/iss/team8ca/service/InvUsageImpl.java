
package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.repo.InvUsageRepo; 
import sg.edu.iss.team8ca.repo.TransHistoryRepo;
import sg.edu.iss.team8ca.repo.UsageDetailsRepo;

@Service
@Transactional
public class InvUsageImpl implements InvUsageInterface {

	@Autowired
	InvUsageRepo invUsageRepo;

	@Autowired
	UsageDetailsRepo usageDetailsRepo;

	@Autowired
	TransHistoryRepo transHistoryRepo;

	@Override
	@Transactional(readOnly = true)
	public InvUsage findInvUsage(long id) {  
		//  TODO Auto-generated method stub 
		return null;
	}

	@Override
	@Transactional(readOnly = true) 
	public List<InvUsage> listInvUsageById(long id) { 
		// TODO Auto-generated method stub 
		return null; 
	}
	
	@Override
	@Transactional(readOnly = true) 
	public List<InvUsage>listInvUsageByDate(long id, Date startDate, Date endDate) { 
		// TODO Auto-generated method stub 
		return null; 
	}

	@Override
	@Transactional 
	public void deleteInvUsage(long id) { 
		// TODO Auto-generated method stub 
	}

	@Override
	@Transactional
	public void saveUsage(InvUsage invUsage) {
		invUsageRepo.save(invUsage);
	}

	@Override
	@Transactional
	public void saveUsageDetails(UsageDetails usageDetails) {
		usageDetailsRepo.save(usageDetails);
	}

}
