package co.company.spring.users.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.company.spring.users.service.UserService;
import co.company.spring.users.service.UserVO;

@RestController
public class RestfulUserController {
	@Autowired
	UserService userService;
	
	//전체조회(GET)
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<UserVO> getUserList(Model model, UserVO vo) {
		return  userService.getUserList(vo);
	}
	
	//단건조회(GET)
	@RequestMapping(value="/users/{id}",  method=RequestMethod.GET)
	public UserVO getUser(@PathVariable String id, UserVO vo, Model model) {
		vo.setId(id);
		return  userService.getUser(vo);
	}
	
	//삭제(DELETE)
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public Map  getUserList(@PathVariable String id, UserVO vo, Model model) {
		vo.setId(id);
		userService.deleteUser(vo);
		Map result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}
	
	//등록(POST)
	@RequestMapping(value="/users"
			,method=RequestMethod.POST )
	//		,produces="application/json"     
	// 		,consumes="application/json"
    //      ,headers = {"Content-type=application/json" }
	// )
	public Map insertUser(@RequestBody UserVO vo, Model model) { //@RequestBody: json을 파싱해서 vo에 담아줌
		userService.insertUser(vo);
		return  Collections.singletonMap("result", true);
	}
	
	//수정(PUT)
	@RequestMapping(value="/users"
			,method=RequestMethod.PUT
	//		,produces="application/json"      //응답헤더
	// 		,consumes="application/json"      //요청헤더
    //      ,headers = {"Content-type=application/json" }
	)
	public UserVO updateUser(@RequestBody UserVO vo, Model model) {
		userService.updateUser(vo);
		return  vo;
	}	
	
	@RequestMapping(value="/respAPI")
	public Map respAPI() {
		RestTemplate rest = new RestTemplate();
		return rest.getForObject("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101", Map.class);
	}
	
}
