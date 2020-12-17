package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Inventory;

public interface ReorderReportInterface {

	public List<Inventory> reoderReport(long id);
}
