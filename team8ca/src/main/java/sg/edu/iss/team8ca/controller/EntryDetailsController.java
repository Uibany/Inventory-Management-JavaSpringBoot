
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
@RequestMapping("/entryform")
public class EntryDetailsController {

    @Autowired
    private EntryDetailsInterface edService;

	@Autowired
	public void enterDetailsController(EntryDetailsInterface edService) {
		this.edService = edService;
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		edService.saveProduct(inventory);
		return "ProductListing";
	}
	
	@RequestMapping(value = "/")
	public String viewInventory(Model model) {
		List<Inventory> ilist = edService.list();
		model.addAttribute("ilist", ilist);
		return "ProductListing";
	}
}
