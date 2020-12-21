package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.team8ca.model.Customer;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.service.CustomerImpl;
import sg.edu.iss.team8ca.service.CustomerInterface;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerInterface cuservice;
	
	@Autowired
	public void setCustomerService(CustomerImpl customerImpl) {
		this.cuservice = customerImpl;	
	}
	
	@RequestMapping(value="/list")
	public String list (Model model) {
		int pageSize = 5;
		int pageNo = 1;
		String sortField = "id";
		String sortDirection = "asc";
		Page<Customer> page = cuservice.cusSearchPage("", pageNo, pageSize, sortField, sortDirection);
		List<Customer> cusList = page.getContent();
		model.addAttribute("cusList", cusList);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDirection);
		return "CustomerListing";
	}
	
	@RequestMapping(value = "/search/page/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public String searchWithPage(String keyword ,@PathVariable ( value = "pageNo") int pageNo, 
			@PathVariable ( value = "pageSize") int pageSize, 
			@RequestParam ("sortField") String sortField,
			@RequestParam ("sortDir")String sortDir, Model model)  {
		
		Page<Customer> page = cuservice.cusSearchPage(keyword, pageNo, pageSize, sortField, sortDir);
		List<Customer> cusList = page.getContent();
		
		model.addAttribute("cusList", cusList);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "product-listing";
	}
	
	@RequestMapping(value="/add")
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "CustomerForm";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", cuservice.findCustomerById(id));
		return "CustomerForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "CustomerForm";
		}
		cuservice.saveCustomer(customer);
		return "forward:/customer/list";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id) {
		cuservice.deleteSupplier(cuservice.findCustomerById(id));
		return "forward:/customer/list";
	}	
}
	
