package com.nit.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.model.Employee;
import com.nit.services.IEmployeeMgmtServices;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtServices empService;

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	/*
	 * @GetMapping("/report") public String showEmployeeReport(Map<String, Object>
	 * map) {
	 * System.out.println("EmployeeOperationsController.showEmployeeReport()");
	 * Iterable<Employee> itEmps=empService.getAllEmployees(); map.put("empsList",
	 * itEmps); return "show_Employee_report"; }
	 */
	@GetMapping("/emp_add")
	public String showFormForsaveEmployee(@ModelAttribute("emp") Employee emp) {
		return "register_employee";
	}

	/*
	 * @PostMapping("/emp_add") public String
	 * saveEmployee(@ModelAttribute("emp")Employee emp, Map<String,Object> map) {
	 * System.out.println("EmployeeOperationsController.saveEmployee()"); String
	 * msg=empService.regiterEmployee(emp); Iterable<Employee> itEmps =
	 * empService.getAllEmployees(); map.put("result", msg); map.put("empsList",
	 * itEmps); return "redirect:report"; }
	 */
	/*
	 * @GetMapping("/report") public String showEmployeeReport(Map<String, Object>
	 * map) {
	 * System.out.println("EmployeeOperationsController.showEmployeeReport()");
	 * Iterable<Employee> itEmps=empService.getAllEmployees(); map.put("empsList",
	 * itEmps); return "show_Employee_report"; }
	 * 
	 * @PostMapping("/emp_add") public String
	 * saveEmployee(@ModelAttribute("emp")Employee emp, Map<String,Object> map) {
	 * System.out.println("EmployeeOperationsController.saveEmployee()"); String
	 * msg=empService.regiterEmployee(emp); map.put("resultMsg", msg); return
	 * "redirect:report"; }
	 */

	// Using session object and session attributes
	/*
	 * @PostMapping("/emp_add") public String
	 * saveEmployee(@ModelAttribute("emp")Employee emp, HttpSession ses) {
	 * System.out.println("EmployeeOperationsController.saveEmployee()"); String
	 * msg=empService.regiterEmployee(emp); ses.setAttribute("resultMsg", msg);
	 * return "redirect:report"; }
	 * 
	 * @GetMapping("/report") public String showEmployeeReport(Map<String, Object>
	 * map) {
	 * System.out.println("EmployeeOperationsController.showEmployeeReport()");
	 * Iterable<Employee> itEmps=empService.getAllEmployees(); map.put("empsList",
	 * itEmps); return "show_Employee_report"; }
	 */
	// This is the best solution PRG (post redirect get) pattern

	@GetMapping("/report")
	public String showEmployeeReport(@PageableDefault(page=0,size=3,sort="job",direction = Sort.Direction.ASC)Pageable pageable, Map<String, Object> map) {
		System.out.println("EmployeeOperationsController.showEmployeeReport()");
		Page<Employee> page = empService.getEmployeeReportDataByPage(pageable);
		map.put("empsData", page);
		return "show_Employee_report";
	}

	@PostMapping("/emp_add")
	public String saveEmployee(@ModelAttribute("emp") Employee emp, RedirectAttributes attrs) {
		System.out.println("EmployeeOperationsController.saveEmployee()");
		String msg = empService.regiterEmployee(emp);
		attrs.addFlashAttribute("resultMsg", msg);
		return "redirect:report";
	}
	
	
	@GetMapping("/emp_edit")
	public String showEditEmployeeFormPage(@RequestParam("no") int no, @ModelAttribute("emp") Employee emp) {
		Employee emp1 = empService.getEmployeeByNO(no);
		BeanUtils.copyProperties(emp1, emp);
		return "update_employee";
	}
	@PostMapping("/emp_edit")
	public String editEmployee(RedirectAttributes attrs, @ModelAttribute("emp")Employee emp)
	{
		String msg = empService.updateEmployee(emp);
		attrs.addFlashAttribute("resultMsg", msg);
		return "redirect:report";
	}
	@GetMapping("/emp_delete")
	public String deleteEmployee(RedirectAttributes attrs, @RequestParam int no)
	{
		String msg = empService.deleteEmployeeById(no);
		attrs.addFlashAttribute("resultMsg", msg);
		return "redirect:report";
	}
}
