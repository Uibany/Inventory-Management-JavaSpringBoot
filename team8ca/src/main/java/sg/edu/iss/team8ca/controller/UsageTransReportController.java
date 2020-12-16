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
		ModelAndView modelAndView = new ModelAndView();
		String id= request.getParameter("productId");
		long productId = Long.parseLong(id);
		Inventory product = thservice.findInvById(productId);
		//if product not found
		if(product == null) 
		{
			modelAndView.addObject("error", "invalid");
			modelAndView.setViewName("usage-trans-form");
	        return modelAndView;
		}	
		//if no date range provided
		if(request.getParameter("startDate").isBlank()) 
		{
			List<TransHistory> alltransHistory = thservice.listTransHisForId(productId);
			modelAndView.addObject("transHistory", alltransHistory);
			modelAndView.setViewName("usage-trans-form");
			modelAndView.addObject("product", product);
	        return modelAndView;	
		}
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		LocalDate start = LocalDate.parse(startDate); 
		LocalDate end = LocalDate.parse(endDate); 
		List<TransHistory> transHistory = thservice.listTransHisForDate(productId, start, end);
		//if no inventory found for selected dates
		if(transHistory.size()== 0) 
		{
			modelAndView.addObject("error", "invalid-date");
			modelAndView.setViewName("usage-trans-form");
	        return modelAndView;
		}
		
		modelAndView.addObject("transHistory", transHistory);
		modelAndView.addObject("product", product);
		modelAndView.addObject("start", start);
		modelAndView.addObject("end", end);	
		modelAndView.setViewName("usage-trans-form");
        return modelAndView;
    }
	
	
		

}
