package sg.edu.iss.team8ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.team8ca.service.ReorderReportInterface;
import sg.edu.iss.team8ca.service.ReorderReportService;

@Controller
@RequestMapping("/report")
public class ReorderReportController {

	@Autowired
	ReorderReportInterface reorint;
	
	@Autowired
	public void setReorderReportService(ReorderReportService reorser) {
		this.reorint = reorser;
	}
	
	@RequestMapping(value = "/reorder")
	public String list(@PathVariable("id") Long id,Model model) {
		model.addAttribute("inventory", reorint.reoderReport(id));
		return "Reorder-Report";
	}
}
