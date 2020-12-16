package sg.edu.iss.team8ca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.service.UserInterface;
import sg.edu.iss.team8ca.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserInterface crudint;
	
	@Autowired
	public void setUserService(UserService crudservice) {
		this.crudint = crudservice;
	}
    
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("user", crudint.findAllUser());
		return "users";
	}
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("user", new User());
		return "user-form";
	}
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", crudint.findUserById(id));
		return "user-form";
	}
	@RequestMapping(value = "/save")
	public String saveUser(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult,  Model model) {
//		if (bindingResult.hasErrors()) {
//			return "user-form";
//		}
		crudint.saveUser(user);
		return "forward:/user/list";
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		crudint.deleteUser(crudint.findUserById(id));
		return "forward:/user/list";
	}

}