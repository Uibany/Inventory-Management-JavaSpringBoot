package sg.edu.iss.team8ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import antlr.collections.List;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.service.ProductListingInterface;

@Controller
@RequestMapping("/ProductListing")

public class ProductListingController {  
	
	@GetMapping("/entrydetails")
	public String showEntryDetailsForm(Model model) {
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);    		
		return "entrydetails";
	}
	
	
	@Autowired
	private ProductListingInterface plservice;
	
	
	@Autowired
	InventoryRepo irepo; 
 	

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Inventory> plist = plservice.list();
		model.addAttribute("plist", plist);
		return "productlisting";
	}
}
