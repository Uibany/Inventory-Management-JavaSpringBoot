package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Subcategory;
import sg.edu.iss.team8ca.service.ProductListingImpl;
import sg.edu.iss.team8ca.service.ProductListingInterface;

@Controller
@RequestMapping("/inventory")
public class ProductListingController {  
	
	@Autowired
	private ProductListingImpl plService;

	@Autowired
	public void setProductListing(ProductListingImpl inventory) {
		this.plService = inventory;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Inventory> plist = plService.list();
		LocalDate today = LocalDate.now();
		model.addAttribute("plist", plist);
		model.addAttribute("today", today.toString());
		return "productlisting";
	}
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Inventory inventory = new Inventory();
//		List<Brand> brands = plService.listBrand();
//		List<Subcategory> subcats = plService.listSubcategory();
		model.addAttribute("inventory", inventory);
//		model.addAttribute("brands", brands);
//		model.addAttribute("subcats", subcats);
		return "entry-form";
	}
	
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("inventory") Inventory inventory, Model model) {
		plService.saveProduct(inventory);
//		List<Inventory> plist = plService.list();
//		model.addAttribute("plist", plist);
		List<Brand> brands = plService.listBrand();
		List<Subcategory> subcats = plService.listSubcategory();
//		model.addAttribute("inventory", inventory);
		model.addAttribute("brands", brands);
		model.addAttribute("subcats", subcats);
		return "forward:/inventory/list";
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable ( value = "id") long id, Model model) {
		Inventory inventory = plService.findProductById(id);
		model.addAttribute("inventory", inventory);
		return "product-listing";	
	}
	
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
		public String deleteProduct(@PathVariable("id") Long id) {
			plService.deleteProduct(plService.findProductById(id));

		return "redirect:/inventory/list";
	}
	
	@RequestMapping(value = "/addbrand", method = RequestMethod.GET)
	public String addBrand(@ModelAttribute("brand") Brand brand) {
		plService.addBrand(brand);
		return "entry-form";	
	}
	
	@RequestMapping(value = "/addsubcategory", method = RequestMethod.GET)
	public String addSubcategory(@ModelAttribute("subcategory") Subcategory subcategory) {
		plService.addSubcategory(subcategory);
		return "entry-form";	
	}
}
