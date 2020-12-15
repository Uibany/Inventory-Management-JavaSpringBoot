package sg.edu.iss.team8ca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team8ca.model.InvUsage;
import sg.edu.iss.team8ca.model.Inventory;
import sg.edu.iss.team8ca.model.TransHistory;
import sg.edu.iss.team8ca.model.UsageDetails;
import sg.edu.iss.team8ca.service.InvUsageImpl;
import sg.edu.iss.team8ca.service.TransHistoryImpl;

@Controller
@RequestMapping("/report")
public class UsageTransReportController {
	@Autowired
	private TransHistoryImpl thservice; 
	
	
	@Autowired
	public void setUsageReport(TransHistoryImpl usageReport) {
		this.thservice = usageReport;
	}
	
	@RequestMapping(value ="/form", method = RequestMethod.GET)
	public ModelAndView reportform(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usage-trans-form");
        return modelAndView;
    }
	
	
	@RequestMapping(value ="/generate", method=RequestMethod.POST)
	public ModelAndView generateReport(WebRequest request){
		String id= request.getParameter("productId");
		long productId = Long.parseLong(id);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		LocalDate start = LocalDate.parse(startDate); 
		LocalDate end = LocalDate.parse(endDate); 
		
		List<TransHistory> transHistory = thservice.listTransHisForInvId(productId, start, end);
		Inventory product = thservice.findInvById(productId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("transHistory", transHistory);
		modelAndView.addObject("product", product);
		modelAndView.addObject("start", start);
		modelAndView.addObject("end", end);	
		modelAndView.setViewName("usage-trans");
        return modelAndView;
    }
	
	
	
//	@RequestMapping(value="/list", method = RequestMethod.GET)
//	public String listReport(@Param("productId") Long productId, @Param("startDate") String startDate, @Param("endDate") String endDate, Model model) 
//	{
//		LocalDate start = LocalDate.parse(startDate); 
//		LocalDate end = LocalDate.parse(startDate); 
//		List<TransHistory> transHistory = thservice.listTransHisForInvId(productId, start, end);
//		Inventory product = thservice.findInvById(productId);
//		model.addAttribute("transHistory", transHistory);
//		model.addAttribute("product",product);
//		model.addAttribute("start",start);
//		model.addAttribute("end",end);
//		return "usage-trans";	
//	}
//	
	
//	@RequestMapping(value="/list", method = RequestMethod.GET)
//	public String listReport(Model model) 
//	{
//		List<TransHistory> transHistory = thservice.listAllTransHis();
////		Inventory product = thservice.findInvById(productId);
//		model.addAttribute("transHistory", transHistory);
////		model.addAttribute("product",product);
////		model.addAttribute("start",start);
////		model.addAttribute("end",end);
//		return "usage-trans";	
//	}
	

}
