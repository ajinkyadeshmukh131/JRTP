//EmployeeOperationsController
package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.IEmployeeMgmtService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private  IEmployeeMgmtService  empService;
	
	
	@GetMapping("/")
	public  String showHome() {
		return "welcome";
	}
	
	@GetMapping("/report")
	public String   showReport(Map<String,Object> map) {
		System.out.println("EmployeeOperationsController.showReport()");
		//use service
		List<Employee> empsList=empService.fetchAllEmployees();
		//keep the results in Model attribute
		map.put("empsInfo", empsList);
		// return LVN
		return "show_report";
	}
	
	@GetMapping("/register")
	public  String   showRegisterEmployeeFormPage(@ModelAttribute("emp") Employee emp) {
		System.out.println("EmployeeOperationsController.showRegisterEmployeeFormPage()");
		//return  LVN
		return  "employee_register_form";
	}
	
	/*@PostMapping("/register")   // gives  Double Posting or Duplicate formSubmission problem
	public   String   processRegisterEmployeeFormPage(Map<String,Object> map,
			                                                                                     @ModelAttribute("emp") Employee emp) {
		//use service class
		String msg=empService.registerEmployee(emp);
		List<Employee> list=empService.fetchAllEmployees();
		//keep the results in Model attribute
		 map.put("resultMsg", msg);
		 map.put("empsInfo",list);
		 //retrun LVN
		 return "show_report";
	}*/
	
	/*@PostMapping("/register")   //  PRG Pattern ---> solves  Double Posting Problem
	public   String   processRegisterEmployeeFormPage(Map<String,Object> map,
			                                                                                     @ModelAttribute("emp") Employee emp) {
		System.out.println("EmployeeOperationsController.processRegisterEmployeeFormPage()");
		//use service class
		String msg=empService.registerEmployee(emp);
		//keep the results in Model attribute
		 map.put("resultMsg", msg);
		 //retrun LVN
		 return "redirect:report";
	}*/
	
	/*@PostMapping("/register")   //  PRG Pattern ---> solves  Double Posting Problem  + Flash attributes
	public   String   processRegisterEmployeeFormPage(RedirectAttributes attrs,
			                                                                                     @ModelAttribute("emp") Employee emp) {
		System.out.println("EmployeeOperationsController.processRegisterEmployeeFormPage()");
		//use service class
		String msg=empService.registerEmployee(emp);
		//keep the results in Model attribute
		 attrs.addFlashAttribute("resultMsg", msg);
		 //retrun LVN
		 return "redirect:report";
	}*/
	
	@PostMapping("/register")   //  PRG Pattern ---> solves  Double Posting Problem  + Session Attributes
	public   String   processRegisterEmployeeFormPage(HttpSession ses,
			                                                                                     @ModelAttribute("emp") Employee emp) {
		System.out.println("EmployeeOperationsController.processRegisterEmployeeFormPage()");
		//use service class
		String msg=empService.registerEmployee(emp);
		//keep the results in Model attribute
		 ses.setAttribute("resultMsg", msg);
		 //retrun LVN
		 return "redirect:report";
	}
	
	
	@GetMapping("/edit")
	public  String  showEditFormPage(@RequestParam("no") int no,
			                                                    @ModelAttribute("emp")  Employee emp) {
		//use service
		Employee  emp1=empService.getEmployeeByNo(no);
		//keep  emp1 object data to Model class obj emp
		BeanUtils.copyProperties(emp1, emp);  
		//return LVN
		return "employee_edit_form";
		
	}
	
	@PostMapping("/edit")
	public  String  processEditFormPage(RedirectAttributes attrs,
			                                                    @ModelAttribute("emp")  Employee emp) {
		//use service
		String msg=empService.updateEmployee(emp);
		  //keep the result in Model attribute
		  attrs.addFlashAttribute("resultMsg",msg);
		//return LVN
		return "redirect:report";
		
	}
	
	
	@GetMapping("/delete")
	public    String  deleteEmployeeByNo(@RequestParam("no") int no,
			                                                           RedirectAttributes attrs) {
		//use service
		String  msg=empService.deleteEmployee(no);
		//keep the result in Model attribute
		  attrs.addFlashAttribute("resultMsg",msg);
		//return LVN
		return "redirect:report";
		
	}
	
	

}
