package co.company.spring.controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.company.spring.dao.Emp;

public class EmpValidator implements Validator { // 검증

	public final static String emailRegExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	private Pattern pattern;

	public EmpValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	//Object: 검사할 대상, Errors: 에러메세지를 담을 객체
		Emp emp = (Emp)target;
		//방법1
		if(emp.getLastName() == null || emp.getLastName().trim().isEmpty() ){
			   errors.rejectValue("lastName", "required", new Object[] {"lastName"}, "");
		   }
		//방법2(null값 체크)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobId", "required", new Object[] {"jobId"}, "");
		//이메일형식(@.) 검사
		if(emp.getEmail()==null || emp.getEmail().trim().isEmpty()) {
	         errors.rejectValue("email", "required", new Object[] {"email"}, "");
	      }
	      else {
	         Matcher matcher=pattern.matcher(emp.getEmail());
	         if(! matcher.matches()) {
	            errors.rejectValue("email","bad");
	         }
	      }
	}

}
