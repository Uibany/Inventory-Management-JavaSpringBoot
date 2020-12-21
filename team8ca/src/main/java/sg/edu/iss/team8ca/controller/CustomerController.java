package sg.edu.iss.team8ca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.team8ca.model.Customer;
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
		model.addAttribute("customer", cuservice.findAllCustomer());
		return "CustomerListing";
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
	
