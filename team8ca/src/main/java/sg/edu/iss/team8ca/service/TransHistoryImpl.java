package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
public class TransHistoryImpl implements TransHistoryInterface {

	@Autowired
	TransHistoryRepo threpo;
	@Autowired
	InventoryRepo irepo;
	
	@Override
	@Transactional (readOnly = true)
	public List<TransHistory> listTransHisForInvId(long id, LocalDate startDate, LocalDate endDate) {
		return threpo.listTransHisForInvId(id, startDate, endDate);
	}

	@Override
	@Transactional (readOnly = true)
	public Inventory findInvById(long id) {
		return irepo.findInvById(id);
	}

	@Override
	public List<TransHistory> listTransHis(long id) {
		return threpo.listTransHis(id);
	}

	@Override
	public List<TransHistory> listAllTransHis() {
		return threpo.findAll();
	}
	
	

}
