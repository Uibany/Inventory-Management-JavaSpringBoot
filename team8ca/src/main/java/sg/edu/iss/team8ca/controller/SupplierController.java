package sg.edu.iss.team8ca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.team8ca.model.Supplier;
import sg.edu.iss.team8ca.service.SupplierInterface;
import sg.edu.iss.team8ca.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierInterface supint;
	
	@Autowired
	public void setMemberService(SupplierService supservice) {
		this.supint = supservice;
	}
    
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("supplier", supint.findAllSupplier());
		return "supplier";
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "SupplierForm";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("supplier", supint.findSupplierById(id));
		return "SupplierForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "SupplierForm";
		}
		supint.saveSupplier(supplier);
		return "forward:/supplier/list";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		supint.deleteSupplier(supint.findSupplierById(id));
		return "forward:/supplier/list";
	}

}


