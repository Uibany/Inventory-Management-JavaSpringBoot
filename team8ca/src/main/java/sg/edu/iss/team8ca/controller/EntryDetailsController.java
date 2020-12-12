package sg.edu.iss.team8ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.service.EntryDetailsInterface;

@Controller
@RequestMapping("/enterdetails")
public class EntryDetailsController {

    @Autowired(required=true)
    private EntryDetailsInterface edService;

	@Autowired
	public void enterDetailsController(EntryDetailsInterface edService) {
		this.edService = edService;
	}
	
	@RequestMapping(value = "/entryform", method = RequestMethod.GET)
	public String showForm(Model model) {
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
		return "entryform";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProduct(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		edService.createProduct(inventory);
		return "inventory";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		edService.updateProduct(inventory);
		return "inventory";
	}
	@RequestMapping(value = "/inventorylist")
	public String list(Model model) {
		List<Inventory> ilist = edService.list();
		model.addAttribute("ilist", ilist);
		return "inventory";
	}
}
