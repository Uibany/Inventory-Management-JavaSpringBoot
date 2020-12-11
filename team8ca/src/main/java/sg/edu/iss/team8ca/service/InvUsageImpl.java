package sg.edu.iss.team8ca.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.repo.InvUsageRepo;


@Service
@Transactional
public class InvUsageImpl implements InvUsageInterface {

	@Autowired
	InvUsageRepo InvUrepo;

	@Override
	public InvUsage findInvUsage(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvUsage> listInvUsageById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvUsage> listInvUsageByDate(String productId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInvUsage(long id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
