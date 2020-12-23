package co.company.spring.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/login")	//로그인 페이지로 이동
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")	//로그인 처리
	public String login(HttpSession session) {
		//로그인 처리
		session.setAttribute("loginId", "user");	//로그인 정보를 세션에 저장
		return "redirect:/empSelect";
	}
	
	@GetMapping("/logout")	//로그아웃
	public String logoutForm(HttpSession session) {
		session.invalidate();	//세션 무효화
		return "user/login";
	}
	
}
