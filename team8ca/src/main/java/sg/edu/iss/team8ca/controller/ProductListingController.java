package sg.edu.iss.team8ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.service.EntryDetailsInterface;
import sg.edu.iss.team8ca.service.ProductListingInterface;

@Controller
@RequestMapping("/ProductListing")


public class ProductListingController {  
	
	@Autowired
	private ProductListingInterface plService;
	
	@Autowired
    private EntryDetailsInterface edService;
	
	@Autowired
	InventoryRepo irepo; 

	
	@RequestMapping("/entrydetails")

	 
		@Autowired
		public void enterDetailsController(EntryDetailsInterface edService) {
			this.edService = edService; 
		}
		
		@RequestMapping(value = "/createproduct", method = RequestMethod.POST)
		public String saveProduct(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
			edService.saveProduct(inventory);
			return "inventory";

		}

	
		

	@RequestMapping(value = "/inventory")
	
	@Autowired
	public void viewProductController(ProductListingInterface plService) {
		this.plService = plService; 
	}
	
	public String list(Model model) {
		List<Inventory> plist = plService.list();
		model.addAttribute("plist", plist);
		return "productlisting";
	}



}
