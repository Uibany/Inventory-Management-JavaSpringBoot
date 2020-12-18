package sg.edu.iss.team8ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team8ca.model.Category;
import sg.edu.iss.team8ca.service.ProductListingImpl;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ProductListingImpl plService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Category> clist = plService.listCategory();
		model.addAttribute("clist", clist);
		return "add-category";
	}
	
	@RequestMapping(value = "/add")
	public String addCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "add-category";
		
	}
	
	@RequestMapping(value = "/save")
	public String saveCategory(@ModelAttribute("category") Category category, Model model) {
		plService.addCategory(category);
		return "redirect:/subcategory/add"; 
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)		
	public String deleteCategory(@PathVariable Long id) {
		plService.deleteCategory(plService.findCategoryById(id));
	return "redirect:/category/list";
	}
}
