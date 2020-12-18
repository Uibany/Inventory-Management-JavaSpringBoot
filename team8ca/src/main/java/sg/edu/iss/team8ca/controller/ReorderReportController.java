package sg.edu.iss.team8ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.team8ca.repo.InventoryRepo;
import sg.edu.iss.team8ca.service.ReorderReportService;

@Controller
@RequestMapping("/report")
public class ReorderReportController {

	@Autowired
	InventoryRepo invrepo;
	
	@Autowired
	ReorderReportService reorser;

	@RequestMapping(value = "/reorder")
	public String list(Model model) {
		reorser.printDatFile();
		return "Reorder-Report";
	}
}
