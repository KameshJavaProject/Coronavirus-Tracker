package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;
import com.example.demo.services.CoronaVirusDataServicesRepository;


@Controller
public class HomeController 
{
	
	CoronaVirusDataServices crnService;
	
	@Autowired
	CoronaVirusDataServicesRepository repository;
	
	@Autowired
	public void setCrnService(CoronaVirusDataServices crnService) {
		this.crnService = crnService;
	}


	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStates> allstates=crnService.getAllstates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		repository.saveAll(allstates);
		return "home";
		
	}
	
	 @GetMapping("/viewChart")
	    public String viewChart() {
	        return "ViewChart";
	    }
	
	  @RequestMapping(path="/CollectChartData",produces= {"application/json"})
	  @ResponseBody 
	  public List<LocationStates> CollectChartData(Model model) {
	  System.out.println("View Chart Data...");
	  List<LocationStates>allstates=crnService.getAllstates(); 
	  int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
	  model.addAttribute("LocationStates",allstates);
	  model.addAttribute("Total Deaths Reported",totalDeathsReported);
	  return allstates;
	  }
	  
	 @RequestMapping(value="/ViewChart",method=RequestMethod.GET) 
	 public ModelAndView ViewChart()
	 {
	  return new ModelAndView("ViewChart").addObject("myURL",new String("http://localhost:8228/CollectChartData"));
	 }
	 
}
