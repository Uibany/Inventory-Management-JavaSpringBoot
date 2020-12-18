package sg.edu.iss.team8ca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.Brand;
import sg.edu.iss.team8ca.model.Supplier;
import sg.edu.iss.team8ca.service.ProductListingImpl;
import sg.edu.iss.team8ca.service.SupplierService;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private ProductListingImpl plService;
	@Autowired
	private SupplierService supService;
	
	@RequestMapping(value = "/add")
	public String addBrand(Model model) {
		Brand brand = new Brand();
		List<Brand> blist = plService.listBrand();
		ArrayList<String> slist = supService.findAllSupplierNames();
		model.addAttribute("blist", blist);
		model.addAttribute("brand", brand);
		model.addAttribute("supnames", slist);
		return "add-brand";
	}
	
	@RequestMapping(value = "/save")
	public String saveBrand(@ModelAttribute("brand") Brand brand, Model model) {
		Supplier supplier = supService.findSupplierByName(brand.getSupplier().getCompanyName());
		brand.setSupplier(supplier);
		plService.addBrand(brand);
		return "redirect:/brand/add"; 
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)		
	public String deleteBrand(@PathVariable Long id) {
		plService.deleteBrand(plService.findBrandById(id));
	return "redirect:/brand/add";
	}
}
