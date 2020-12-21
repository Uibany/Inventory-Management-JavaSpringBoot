package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.Subcategory;
import sg.edu.iss.team8ca.model.Supplier;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.TransType;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.service.ProductListingImpl;
import sg.edu.iss.team8ca.service.ReorderReportService;
import sg.edu.iss.team8ca.service.SupplierInterface;
import sg.edu.iss.team8ca.service.SupplierService;
import sg.edu.iss.team8ca.service.TransHistoryImpl;
import sg.edu.iss.team8ca.service.UserService;

@Controller
@RequestMapping("/inventory")
public class ProductListingController {  
	
	@Autowired
	private SupplierInterface supint;
	
	@Autowired
	ReorderReportService reorser;

	@Autowired
	private SupplierService spservice;
	
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
		int pageSize = 5;
		int pageNo = 1;
		String sortField = "id";
		String sortDirection = "asc";
		Page<Inventory> page = plService.findPaginated("", pageNo, pageSize, sortField, sortDirection);
		List<Inventory> plist = page.getContent(); 
		LocalDate today = LocalDate.now();
		model.addAttribute("plist", plist);
		model.addAttribute("today", today.toString());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDirection);
	
		return "product-listing";
	}
	

	@RequestMapping(value = "/search/page/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public String searchWithPage(String keyword ,@PathVariable ( value = "pageNo") int pageNo, 
			@PathVariable ( value = "pageSize") int pageSize, 
			@RequestParam ("sortField") String sortField,
			@RequestParam ("sortDir")String sortDir, Model model)  {
		
		Page<Inventory> page = plService.findPaginated(keyword, pageNo, pageSize, sortField, sortDir);
		List<Inventory> plist = page.getContent();
		
		LocalDate today = LocalDate.now();
		model.addAttribute("plist", plist);
		model.addAttribute("today", today.toString());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
	
	
		return "product-listing";
	}

	@RequestMapping(value = "/addproduct")
	public String addProduct(Model model) {
		Inventory inventory = new Inventory();
		ArrayList<String> blist = plService.findAllBrandNames();
		ArrayList<String> slist = plService.findAllSubcatNames();
		ArrayList<String> splist = spservice.findAllSupplierNames();
		ArrayList<String> clist = plService.findAllCategoryNames(); 
		model.addAttribute("inventory", inventory);
		model.addAttribute("bnames", blist);
		model.addAttribute("snames", slist);
		model.addAttribute("spnames", splist);
		model.addAttribute("cnames", clist);
		model.addAttribute("addOrEdit", "add");
		return "entry-form";
	}
	
	@RequestMapping(value = "/saveproduct")
	public String saveProduct(@ModelAttribute("inventory") @Valid Inventory inventory,
			BindingResult bindingResult, Model model, WebRequest request) {
		
		if (bindingResult.hasErrors()) {
			return "entry-form";
		}
				
		if(request.getParameter("newBrand").equals("false")==true) {
			Brand brand = plService.findBrandByName(inventory.getBrand().getBrandName());
			inventory.setBrand(brand);
		}else{
			String newBrandName = request.getParameter("newBrandName");
			String newBrandManu = request.getParameter("manufacturerName");

			if(request.getParameter("newSupplier").equals("false")==true) 
			{
				Supplier supplier = spservice.findSupplierByName(request.getParameter("companyName"));
				Brand brand = new Brand(newBrandName,newBrandManu,supplier);
				plService.addBrand(brand);
				inventory.setBrand(brand);
			}else{
				String newCompanyName = request.getParameter("newCompanyName");
				String contactNo = request.getParameter("contactNo");
				String address = request.getParameter("address");
				String email = request.getParameter("email");
				int postalCode = Integer.parseInt(request.getParameter("postalCode"));
				Supplier supplier = new Supplier(newCompanyName,contactNo,address,email,postalCode);
				spservice.saveSupplier(supplier);
				Brand brand = new Brand(newBrandName,newBrandManu,supplier);
				plService.addBrand(brand);
				inventory.setBrand(brand);
			}
		}
			if(request.getParameter("newSubcat").equals("false")==true) {
				Subcategory subcat = plService.findSubcatByName(inventory.getSubcategory().getSubcategoryName());
				inventory.setSubcategory(subcat);
				
			}else{
			
				String newSubcatName = request.getParameter("newSubcatName");
				String newSubcatType = request.getParameter("newSubcatType");

				if(request.getParameter("newCategory").equals("false")==true) 
				{
					Category category = plService.findCatByName(request.getParameter("categoryName"));
					Subcategory subcat = new Subcategory(newSubcatName, newSubcatType, category);
					plService.addSubcategory(subcat);
					inventory.setSubcategory(subcat);
					
				}else{
					
					String newCategoryName = request.getParameter("newCategoryName");
					Category category = new Category(newCategoryName);
					plService.addCategory(category);
					Subcategory subcat = new Subcategory(newSubcatName, newSubcatType, category);
					plService.addSubcategory(subcat);
					inventory.setSubcategory(subcat);
				}
			}
			
		Subcategory subcategory = plService.findSubcatByName(inventory.getSubcategory().getSubcategoryName());
		inventory.setSubcategory(subcategory);
				
//		//Add to transHistory	
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		User user1 = uservice.findUserByUserName("admin");
		if(model.getAttribute("addOrEdit")=="add"){
			TransHistory trans = new TransHistory(TransType.NewInventory, Math.toIntExact(inventory.getStockQty()), inventory, LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user1);
			plService.saveProduct(inventory);
			thservice.saveTrans(trans);
			
		}else {
			plService.saveProduct(inventory);
			TransHistory trans = new TransHistory(TransType.UpdateInventory, Math.toIntExact(0), inventory, LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user1);
			thservice.saveTrans(trans);	
		}
		return "redirect:/inventory/list";
	
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable ( value = "id") long id, Model model) {
		ArrayList<String> blist = plService.findAllBrandNames();
		ArrayList<String> slist = plService.findAllSubcatNames();
		ArrayList<String> splist = spservice.findAllSupplierNames();
		ArrayList<String> clist = plService.findAllCategoryNames(); 
		model.addAttribute("inventory", plService.findProductById(id));
		model.addAttribute("bnames", blist);
		model.addAttribute("snames", slist);
		model.addAttribute("spnames", splist);
		model.addAttribute("cnames", clist);
		model.addAttribute("addOrEdit", "edit");
		return "entry-form";	
		}

//	@RequestMapping(value = "/saveeditproduct")
//	public String saveeditProduct(@ModelAttribute("inventory") @Valid Inventory inventory,
//			BindingResult bindingResult, Model model) {
//		
//		if (bindingResult.hasErrors()) {
//			return "edit-form";
//		}
//		Brand brand = plService.findBrandByName(inventory.getBrand().getBrandName());
//		Subcategory subcategory = plService.findSubcatByName(inventory.getSubcategory().getSubcategoryName());
//		inventory.setBrand(brand);
//		inventory.setSubcategory(subcategory);
//		plService.addProduct(inventory);
//		
////		//Add to transHistory	
////		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
////		User user = uservice.findUserByUserName(currentUserName);
////		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
//		User user1 = uservice.findUserByUserName("admin");
//		TransHistory trans = new TransHistory(TransType.UpdateInventory, 0, inventory, LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user1);
//		thservice.saveTrans(trans);
//		return "redirect:/inventory/list";
//	}
		
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)		
		public String deleteProduct(@PathVariable Long id) {
			plService.deleteProduct(plService.findProductById(id));
		return "redirect:/inventory/list";
	}
		
	@RequestMapping("/search")
	public String search(Model model, @Param("keyword") String keyword) {
		List<Inventory> plist = plService.list(keyword);
		LocalDate today = LocalDate.now();
		model.addAttribute("plist", plist);
		model.addAttribute("today", today.toString());
		model.addAttribute("keyword", keyword);
		return "product-listing";
	}
<<<<<<< HEAD
	@RequestMapping("/report/{id}")
	public String reorderReport(@PathVariable("id") long id, Model model) {
		model.addAttribute("message", reorser.printDatFile(id));
=======
	@RequestMapping("/select")
	public String selectSupplier(Model model) {
		model.addAttribute("supplier", supint.findAllSupplier());
		return "reorderreport";
	}
	
	@RequestMapping("/report/{id}")
	public String reorderReport(@PathVariable("id") long id,Model model) {
		model.addAttribute("message",reorser.printDatFile(id));
>>>>>>> branch 'main' of https://github.com/seano188/javaTeam8CA.git
		return "message";
	}
	
}
