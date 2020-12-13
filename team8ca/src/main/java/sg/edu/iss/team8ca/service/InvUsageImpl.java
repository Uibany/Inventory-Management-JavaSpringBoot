
package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.repo.InvUsageRepo;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.repo.TransHistoryRepo;
import sg.edu.iss.team8ca.repo.UsageDetailsRepo;

@Service
@Transactional
public class InvUsageImpl implements InvUsageInterface {

	@Autowired
	InvUsageRepo iurepo;

	@Autowired
	UsageDetailsRepo udrepo;

	@Autowired
	TransHistoryRepo threpo;
	
	@Autowired
	InventoryRepo irepo;
	
//	record creation
	@Override
	@Transactional
	public void addUsage (InvUsage invUsage) {
		iurepo.save(invUsage);
	}
	
	@Override
	@Transactional
	public void addUsageDetails (UsageDetails usageDetails){
		udrepo.save(usageDetails);
	};
	
	@Override
	@Transactional
	public void addTransHistory (TransHistory transHistory) {
		threpo.save(transHistory);
	};
	
//	record deletion
	@Override
	@Transactional
	public void deleteUsage (InvUsage invUsage) {
		iurepo.delete(invUsage);
	};
	
	@Override
	@Transactional
	public void deleteUsageDetails (UsageDetails usageDetails) {
		udrepo.delete(usageDetails);
	};
	
	@Override
	@Transactional
	public void deleteTransHistory(TransHistory transHistory) {
		threpo.delete(transHistory);
	};
	
//	display record
	@Override
	@Transactional (readOnly = true)
	public List<InvUsage> listAllUsageRecord(){
		return iurepo.findAll();
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<UsageDetails> listDetailsForUdId(Long id){
		return udrepo.findUdById(id);
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<UsageDetails> listUsageForInvId(Long id, LocalDate startDate, LocalDate endDate){
	
		return null;
//		to edit
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<Inventory> listAllInventory(){
		return irepo.findAll();
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<Inventory> listMatchingInventory(String keyword){
		return null;
//		to edit
	};
	
//	update record
	@Transactional
	public void updateUsageDetails(UsageDetails usageDetails) {
		
	};
	
	@Transactional
	public void reduceInventory(int quantity) {
		
	};
	
//	inventory.setStockQty(inventory.getStockQty() - quantity);
	

	
	
	
	
	
	
	
	
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
