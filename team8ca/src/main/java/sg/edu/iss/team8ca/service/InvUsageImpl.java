package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
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
	
	public List<UsageDetails> listAllUsageDetails(){
		return udrepo.findAll();
	};	
	
	@Override
	@Transactional (readOnly = true)
	public InvUsage findUsageById(Long id){
		return iurepo.findById(id).get();
	};	
	
	@Override
	@Transactional (readOnly = true)
	public UsageDetails findUsageDetailsById(Long id){
		return udrepo.findById(id).get();
	};	
	
	@Override
	@Transactional (readOnly = true)
	public List<UsageDetails> listDetailsForUdId(Long id){
		return udrepo.findUdById(id);
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<UsageDetails> listUsageForInvId(Long id, LocalDate startDate, LocalDate endDate){	
		return udrepo.listUsageForInvId(id, startDate, endDate);
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<Inventory> listAllInventory(){
		return irepo.findAll();
	};
	
	@Override
	@Transactional (readOnly = true)
	public List<Inventory> invSearch(String keyword){
		return irepo.invSearch(keyword);
	};
	
	@Override
	@Transactional (readOnly = true)
	public Inventory findInvById(Long id){
		return irepo.findById(id).get();
	};
	
	
//	update record
	@Transactional
	public void updateUsageDetails(UsageDetails usageDetails) {
		
	};
	
	@Transactional
	public void reduceInventory(int quantity, Inventory inventory) {
		inventory.setStockQty(inventory.getStockQty() - quantity);
	};
}
