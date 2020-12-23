package co.company.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpMapper;
import co.company.spring.dao.EmpSearch;

@RestController
public class EmpRestController {

	@Autowired EmpMapper dao;
	
	@RequestMapping("/ajax/empSelect")
	public List<Emp> empSelect(EmpSearch emp) {
		return dao.getEmpList(emp);
	}
}
