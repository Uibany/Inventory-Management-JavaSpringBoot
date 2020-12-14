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
@RequestMapping("/usageform")
public class UsageFormController {
	
	@Autowired
	private InvUsageImpl iuservice;
	
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
	@RequestMapping(value ="/addusagereport", method = RequestMethod.POST)
	public String addUsageReport(Model model) {
		InvUsage invUsage = new InvUsage();
		List<Inventory> invList = iuservice.listAllInventory();
		List<UsageDetails> udList = iuservice.listDetailsForUdId(invUsage.getId());
		model.addAttribute("invUsage", invUsage);
		model.addAttribute("udList", udList);	
		model.addAttribute("invList", invList);
		return "UsageReport";
	}
	
//	Update inventory details
	@RequestMapping(value = "/usagereport/{id}", method = RequestMethod.GET)
	public String mapInvInvUsage (@PathVariable("id") Long id, Model model) {
		List<Inventory> invList = iuservice.listAllInventory();
		List<UsageDetails> udList = iuservice.listDetailsForUdId(id);
		model.addAttribute("udList", udList);
		model.addAttribute("invList", invList);
		return "UsageReport";
	}
	
//	Adding inventory items to the usage listing
	@RequestMapping (value = "/usagereport/{id1}/addinvtolist/{id2}", method = RequestMethod.GET)
	public String addListingInv (@PathVariable("id1")Long usageid,@PathVariable("id2") Long invid, Model model) {
		Inventory inv = iuservice.findInvById(invid);
		UsageDetails ud = new UsageDetails(inv, iuservice.findUsageById(usageid));
		return "UsageReport";		
	}
	
	@RequestMapping (value = "/usagereport/{id}/updateusage/{id2}/", method = RequestMethod.GET)
	public String updateInvUsage (@RequestParam("qty"), @PathVariable("id1") Long usageid,@PathVariable("id2") Long invid, Model model) {
		asda
	}
	
	

	

}
