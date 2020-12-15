package sg.edu.iss.team8ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.service.ProductListingInterface;

@Controller
@RequestMapping("/inventory")
public class ProductListingController {  
	
	@Autowired
	private ProductListingInterface plService;
		
	@Autowired
	public void setProductListing(ProductListingInterface inventory) {
		this.plService = inventory;
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		plService.saveProduct(inventory);
		return "inventory";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Inventory> plist = plService.list();
		model.addAttribute("plist", plist);
		return "inventory";
	}
	
	@RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable ( value = "id") long id, Model model) {
		Inventory inventory = plService.findProductById(id);
		model.addAttribute("inventory", inventory);
		return "inventory";	
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@ModelAttribute("inventory") Inventory inventory) {
		plService.deleteProduct(inventory);
		return "inventory";
	}
	

}
