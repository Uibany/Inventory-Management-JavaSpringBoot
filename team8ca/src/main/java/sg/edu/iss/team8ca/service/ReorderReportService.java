package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;

@Service
public class ReorderReportService implements ReorderReportInterface {

	private InventoryRepo invrepo;

	public List<Inventory> reoderReport(long id) {
//		return invrepo.reorderreport(id);
		return null;
	}

}
