package co.company.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.company.spring.dao.Dept;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpSearch;
import co.company.spring.dao.Jobs;
import co.company.spring.emp.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	EmpService service;
	
	@ModelAttribute("jobs")
	public List<Jobs> jobs() {
		return service.jobSelect();
	}
	
	@ModelAttribute("dept")
	public List<Dept> dept() {
		return service.deptSelect();
	}

	@RequestMapping("/ajax/jobSelect")
	@ResponseBody
	public List<Jobs> jobSelect() {
		return service.jobSelect();
	}
	
	@RequestMapping(value = "/empSelect", method = RequestMethod.GET)
	public ModelAndView select(EmpSearch emp) {
		ModelAndView mav = new ModelAndView();
		// 조회
		mav.addObject("list", service.getEmpList(emp));
		mav.setViewName("emp/select");
		return mav;
	}

	@GetMapping("/empUpdateForm") 
	public String updateForm(Model model, Emp emp) {
		model.addAttribute("emp", service.getEmp(emp));
		return "emp/insert"; // forward
	}
	
	@GetMapping("/empInsertForm")
	public String insertForm(Model model, Emp emp) {
		return "emp/insert"; // forward
	}

	@PostMapping("/empInsert")
	public String insert(Model model, Emp emp, Errors errors) {
		new EmpValidator().validate(emp, errors);
		if(errors.hasErrors()) {
			return "emp/insert";
		}
		if(emp.getEmployeeId() == null)
			service.insertEmp(emp);
		else
			service.updateEmp(emp);
//		request.setAttribute("emp", emp);
		return "emp/insertOutput";
	}
	
/*	public String insert(HttpServletRequest request,
						 @RequestParam(value = "lastName", required = false, defaultValue = "noname") String lName,
						 @RequestParam(required = false) int salary,
						 Emp emp) {
		// 파라미터
		String fName = request.getParameter("firstName");
		System.out.println(lName + ":" + fName + ":" + salary);
		System.out.println("emp\n" + emp);
		// 등록처리
		// redirect
		return "redirect:/empSelect"; // redirect 지정하지 않으면 default 값은 forward
	}*/
}
