package sg.edu.iss.team8ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.service.InvUsageImpl;

@Controller
@RequestMapping("/usagetransreport")
public class UsageTransReportController {
	@Autowired
	private InvUsageImpl iuservice; 
	
	@RequestMapping(value = "/showusagetrans", method = RequestMethod.GET)
	public String showListing (Model model) {
		model.addAttribute("test", "hello from controller");
		return "testing";
	}
	

}
