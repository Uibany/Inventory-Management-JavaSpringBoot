package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import sg.edu.iss.team8ca.model.Customer;
import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.TransType;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.model.UsageReportStatus;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.service.CustomerImpl;
import sg.edu.iss.team8ca.service.InvUsageImpl;
import sg.edu.iss.team8ca.service.ProductListingImpl;
import sg.edu.iss.team8ca.service.SendEmailService;
import sg.edu.iss.team8ca.service.TransHistoryImpl;
import sg.edu.iss.team8ca.service.UserService;

@Controller
@RequestMapping("/invusage")
public class UsageFormController {

	@Autowired
	private InvUsageImpl iuservice;

	@Autowired
	private UserService uservice;

	@Autowired
	private ProductListingImpl pservice;

	@Autowired
	private SendEmailService sendEmailService;

	@Autowired
	private TransHistoryImpl thservice;

	@Autowired
	private CustomerImpl cuservice;

	@RequestMapping(value = "/showlisting", method = RequestMethod.GET)
	public String showListing(Model model) {
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user);
		User user = uservice.findUserByUserName("admin");
		model.addAttribute("user", user);
		List<InvUsage> usageList = iuservice.listAllUsageRecord();
		model.addAttribute("usageList", usageList);
		return "iulisting";
	}

//	New usage report
	@RequestMapping(value = "/addforms/addformdetails/{userid}", method = RequestMethod.GET)
	public String addUsageReportNewCust(Model model, @PathVariable("userid") Long userid) {
		User user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		List<Customer> customers = cuservice.findAllCustomer();
		model.addAttribute("usageform", new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user));
		model.addAttribute("customers", customers);
		model.addAttribute("customer", new Customer());
		return "UsageReportCustTask";
	}

//	add customers
	@RequestMapping(value = "/addforms/addformdetails/userid/{userid}/customer/{custid}", method = RequestMethod.GET)
	public String addUsageReportExistingCust(Model model, @PathVariable("userid") Long userid,
			@PathVariable("custid") Long custid) {
		User user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		Customer customer = cuservice.findCustomerById(custid);
		List<Customer> customers = cuservice.findAllCustomer();
		model.addAttribute("usageform", new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user));
		model.addAttribute("customers", customers);
		model.addAttribute("customer", customer);
		return "UsageReportCustTask";
	}

//customer search
	@RequestMapping(value = "/addforms/addformdetails/userid/{userid}/customersearch/{custid}", method = RequestMethod.GET)
	public String addUsageCustomerSearch(Model model, @PathVariable("userid") Long userid,
			@PathVariable("custid") Long custid, @Param("keyword") String keyword) {
		User user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		List<Customer> customers = cuservice.cusSearch(keyword);
		model.addAttribute("usageform", new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user));
		model.addAttribute("customers", customers);
		if (custid==0) {
			model.addAttribute("customer", new Customer());
		} else {
			model.addAttribute("customer", cuservice.findCustomerById(custid));
		}

		return "UsageReportCustTask";
	}

//save usage details
	@RequestMapping(value = "/addforms/saveusageform/userid/{userid}", method = RequestMethod.GET)
	public String addUsageFormDetails(Model model, @PathVariable("userid") Long userid, @ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult, WebRequest request) {
		if(bindingResult.hasErrors()) {
			return "UsageReportCustTask";
		}
		else {
			Customer findcustomer = cuservice.findCustomerByNameEmailContactAddress(customer.getCustomerName(), customer.getEmail(), customer.getContactNo(), customer.getAddress());
			if(findcustomer != null) {
				Customer customer1 = cuservice.findCustomerByName(customer.getCustomerName());				
				cuservice.saveCustomer(customer1);				
			}
			else {
				Customer customer1 = new Customer(customer.getCustomerName(),customer.getContactNo(), customer.getEmail(),  customer.getAddress());
				cuservice.saveCustomer(customer1);	
			}
			Customer customer1 = cuservice.findCustomerByName(customer.getCustomerName());
			User user = uservice.findUserById(userid);
			model.addAttribute("user", user);			
			String tasks = request.getParameter("task");
			InvUsage usageform = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user);
			usageform.setCustomer(customer1);
			usageform.setTasks(tasks);
			iuservice.addUsage(usageform);
			return "forward:/invusage/usageforms/" + usageform.getId();
		}
	}

//	Update inventory details
	@RequestMapping(value = "/usageforms/{usageformid}", method = RequestMethod.GET)
	public String mapInvInvUsage(@PathVariable("usageformid") Long usageformid, Model model) {
		List<Inventory> invList = iuservice.listAllInventory();
		List<UsageDetails> udList = iuservice.listDetailsForUdId(usageformid);
		InvUsage iu = iuservice.findUsageById(usageformid);
		model.addAttribute("usageform", iu);
		model.addAttribute("udList", udList);
		model.addAttribute("invList", invList);
		return "usage-details";
	}

//	Update inventory details with search parameters
	@RequestMapping(value = "/usageforms/search/{usageformid}", method = RequestMethod.GET)
	public String invSearch(@PathVariable("usageformid") Long usageformid, @Param("keyword") String keyword,
			Model model) {
		List<Inventory> invList = iuservice.invSearch(keyword);
		List<UsageDetails> udList = iuservice.listDetailsForUdId(usageformid);
		InvUsage iu = iuservice.findUsageById(usageformid);
		model.addAttribute("usageform", iu);
		model.addAttribute("udList", udList);
		model.addAttribute("invList", invList);
		return "usage-details";
	}

//	Adding inventory items to the usage listing
	@RequestMapping(value = "/usageforms/{id1}/addinvtoform/{id2}", method = RequestMethod.GET)
	public String addListingInv(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		Inventory inv = iuservice.findInvById(id1);
		UsageDetails ud = new UsageDetails(inv, iuservice.findUsageById(id2), LocalDate.now(), 0);
		iuservice.addUsageDetails(ud);
		return "forward:/invusage/usageforms/" + id2;
	}

//	Delete usage details
	@RequestMapping(value = "/delete/usageforms/{id1}/ud/{id2}", method = RequestMethod.GET)
	public String deleteUd(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = uservice.findUserByUserName(currentUserName);
//		InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user);	
		User user = uservice.findUserByUserName("admin");
		UsageDetails ud = iuservice.findUsageDetailsById(id2);
		Inventory inventory = pservice.findProductById(ud.getInventory().getId());
		inventory.setStockQty(inventory.getStockQty() + Math.toIntExact(ud.getQuantity()));
		pservice.saveProduct(inventory);
		iuservice.deleteUsageDetails(ud);
		TransHistory trans = new TransHistory(TransType.DebitBack, Math.toIntExact(ud.getQuantity()), inventory,
				LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user);
		thservice.saveTrans(trans);
		return "forward:/invusage/usageforms/" + id1;
	}

//	update usage quantity
	@RequestMapping(value = "/usage/{id1}/ud/{id2}", method = RequestMethod.GET)
	public String usageQuantity(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2,
			@RequestParam("ud_quantity") Long quantity) {
//			String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//			User user = uservice.findUserByUserName(currentUserName);
//			InvUsage invUsage = new InvUsage(LocalDate.now(), UsageReportStatus.InProgress, user1);
		User user = uservice.findUserByUserName("admin");
		UsageDetails ud = iuservice.findUsageDetailsById(id2);
		long udQuantity = ud.getQuantity();
		long newUdQuantity = udQuantity + quantity;
		Inventory inventory = pservice.findProductById(ud.getInventory().getId());
		int invQuantity = inventory.getStockQty();
		int newQuantity = invQuantity - Math.toIntExact(quantity);

		if (newQuantity >= 0 && quantity >= 0) {
			ud.setQuantity(newUdQuantity);
			ud.setDate(LocalDate.now());
			iuservice.addUsageDetails(ud);

			inventory.setStockQty(newQuantity);
			pservice.saveProduct(inventory);

			TransHistory trans = new TransHistory(TransType.Usage, -Math.toIntExact(quantity), inventory,
					LocalDate.now(), LocalTime.now(ZoneId.of("Asia/Tokyo")), user);
			thservice.saveTrans(trans);

			if (inventory.getStockQty() < inventory.getReorderLevel()) {
				String id = String.valueOf(inventory.getId());
				String name = inventory.getProductName();
				sendEmailService.sendEmail("team8caproject@gmail.com",
						"Inventory ID " + id + " quantity on hand is below the re-order level! Please restock!",
						"NOTIFCATION: stock level below re-order level for part number " + id + " - " + name);
			}

			return "forward:/invusage/usageforms/" + id1;
		} else {
			return "forward:/invusage/usageforms/" + id1;
		}
	}

}
