package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.model.UsageReportStatus;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.service.InvUsageImpl;
import sg.edu.iss.team8ca.service.UserService;

@Controller
@RequestMapping("/invusage")
public class UsageFormController {
	
	@Autowired
	private InvUsageImpl iuservice;
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUsageForm(InvUsageImpl usageform) {
		this.iuservice = usageform;
	}
	
	@RequestMapping(value = "/showlisting", method = RequestMethod.GET)
	public String showListing (Model model) {
		List<InvUsage> usageList = iuservice.listAllUsageRecord();
		model.addAttribute("usageList", usageList);
		return "iulisting";
	}
	
//	New usage report
	@RequestMapping(value ="/addforms", method = RequestMethod.GET)
	public String addUsageReport(Model model) {
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user);
		User user1 = uservice.findUserByUserName("admin");
		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		iuservice.addUsage(invUsage);
		List<Inventory> invList = iuservice.listAllInventory();
		List<UsageDetails> udList = iuservice.listDetailsForUdId(invUsage.getId());
		model.addAttribute("invUsage", invUsage);
		model.addAttribute("udList", udList);	
		model.addAttribute("invList", invList);
		return "usage-details";
	}
	
//	Update inventory details
	@RequestMapping(value = "/usageforms/{id}", method = RequestMethod.GET)
	public String mapInvInvUsage (@PathVariable("id") Long id, Model model) {
		List<Inventory> invList = iuservice.listAllInventory();
		List<UsageDetails> udList = iuservice.listDetailsForUdId(id);
		model.addAttribute("udList", udList);
		model.addAttribute("invList", invList);
		return "usage-details";
	}
	
//	Adding inventory items to the usage listing
	@RequestMapping (value = "/usageforms/{id1}/addinvtoform/{id2}", method = RequestMethod.GET)
	public String addListingInv (@PathVariable("id1")Long usageid,@PathVariable("id2") Long invid, Model model) {
		Inventory inv = iuservice.findInvById(invid);
		UsageDetails ud = new UsageDetails(inv, iuservice.findUsageById(usageid), LocalDate.now(), 0);
		return "usage-details";		
	}

//	@RequestMapping (value = "/usagereport/{id}/updateusage/{id2}/", method = RequestMethod.GET)
//	public String updateInvUsage (@RequestParam("qty"), @PathVariable("id1") Long usageid,@PathVariable("id2") Long invid, Model model) {
//		asda
//	}
	
}
