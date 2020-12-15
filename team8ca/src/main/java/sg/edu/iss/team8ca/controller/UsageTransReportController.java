package sg.edu.iss.team8ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.service.InvUsageImpl;
import sg.edu.iss.team8ca.service.TransHistoryImpl;

@Controller
@RequestMapping("/UsageReport")
public class UsageTransReportController {
	@Autowired
	private TransHistoryImpl thservice; 
	
	@Autowired
	public void setUsageReport(TransHistoryImpl usageReport) {
		this.thservice = usageReport;
	}
	
	@GetMapping(value ="/filter")
	public String generateReport () {
		return "usage-trans-form";
	}
	

}
