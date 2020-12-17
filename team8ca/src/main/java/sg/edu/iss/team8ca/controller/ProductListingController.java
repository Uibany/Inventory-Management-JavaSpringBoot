package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Subcategory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.TransType;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.service.ProductListingImpl;
import sg.edu.iss.team8ca.service.TransHistoryImpl;
import sg.edu.iss.team8ca.service.UserService;

@Controller
@RequestMapping("/inventory")
public class ProductListingController {  
	
	@Autowired
	private ProductListingImpl plService;
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private TransHistoryImpl thservice;

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
		return "product-listing";
	}
	
	@RequestMapping(value = "/addproduct")
	public String addProduct(Model model) {
		Inventory inventory = new Inventory();
		ArrayList<String> blist = plService.findAllBrandNames();
		ArrayList<String> slist = plService.findAllSubcatNames();
		model.addAttribute("inventory", inventory);
		model.addAttribute("bnames", blist);
		model.addAttribute("snames", slist);
		return "entry-form";
	}
	
	@RequestMapping(value = "/saveproduct")
	public String saveProduct(@ModelAttribute("inventory") @Valid Inventory inventory,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "entry-form";
		}
		Brand brand = plService.findBrandByName(inventory.getBrand().getBrandName());
		Subcategory subcategory = plService.findSubcatByName(inventory.getSubcategory().getSubcategoryName());
		inventory.setBrand(brand);
		inventory.setSubcategory(subcategory);
		plService.addProduct(inventory);
		
//		//Add to transHistory	
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		User user1 = uservice.findUserByUserName("admin");
		TransHistory trans = new TransHistory(TransType.NewInventory, Math.toIntExact(inventory.getStockQty()), inventory, LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user1);
		thservice.saveTrans(trans);
		return "redirect:/inventory/list";
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable ( value = "id") long id, Model model) {
		ArrayList<String> blist = plService.findAllBrandNames();
		ArrayList<String> slist = plService.findAllSubcatNames();
		model.addAttribute("inventory", plService.findProductById(id));
		model.addAttribute("bnames", blist);
		model.addAttribute("snames", slist);
		return "edit-form";	
	}
	
	@RequestMapping(value = "/saveeditproduct")
	public String saveeditProduct(@ModelAttribute("inventory") @Valid Inventory inventory,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "edit-form";
		}
		Brand brand = plService.findBrandByName(inventory.getBrand().getBrandName());
		Subcategory subcategory = plService.findSubcatByName(inventory.getSubcategory().getSubcategoryName());
		inventory.setBrand(brand);
		inventory.setSubcategory(subcategory);
		plService.addProduct(inventory);
		
//		//Add to transHistory	
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		User user1 = uservice.findUserByUserName("admin");
		TransHistory trans = new TransHistory(TransType.NewInventory, Math.toIntExact(inventory.getStockQty()), inventory, LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user1);
		thservice.saveTrans(trans);
		return "redirect:/inventory/list";
	}
		

	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)		
		public String deleteProduct(@PathVariable Long id) {
			plService.deleteProduct(plService.findProductById(id));
		return "redirect:/inventory/list";
	}
	
	@RequestMapping(value = "/addbrand", method = RequestMethod.GET)
	public String addBrand(@ModelAttribute("brand") Brand brand) {
		plService.addBrand(brand);
		return "add-brand"; 
	}
	
	@RequestMapping(value = "/savebrand", method = RequestMethod.GET)
	public String saveBrand(@ModelAttribute("brand") Brand brand, Model model) {
		plService.addBrand(brand);
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
		return "redirect:/inventory/entry-form"; 
	}
	
	@RequestMapping(value = "/addsubcategory", method = RequestMethod.GET)
	public String addSubcategory(@ModelAttribute("subcategory") Subcategory subcategory) {
		plService.addSubcategory(subcategory);
		return "add-subcategory";	
	}
	
	@RequestMapping(value = "/savesubcat", method = RequestMethod.GET)
	public String saveSubcat(@ModelAttribute("subcategory") Subcategory subcategory, Model model) {
		plService.addSubcategory(subcategory);
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
		return "redirect:/inventory/entry-form"; 
	}
	
}
