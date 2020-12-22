package sg.edu.iss.team8ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@RequestMapping(value = "/add")
	public String addCategory(Model model) {
		Category category = new Category();
		List<Category> clist = plService.listCategory();
		model.addAttribute("category", category);
		model.addAttribute("clist", clist);
		return "add-category";
	}
	
	@RequestMapping(value = "/save")
	public String saveCategory(@ModelAttribute("category") Category category, Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-category";
		}
		plService.addCategory(category);
		return "forward:/category/add"; 
	}
	
	@RequestMapping(value = "/delete/{id}")		
	public String deleteCategory(@PathVariable Long id) {
		plService.deleteCategory(plService.findCategoryById(id));
	return "forward:/category/add";
	}
}
