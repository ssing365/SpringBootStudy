package com.edu.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.springboot.jdbc.AddressDTO;
import com.edu.springboot.jdbc.IAddressService;



@Controller
public class MainController {

	@Autowired
	IAddressService dao;
	
	/*
	동적셀렉트 페이지로 진입시에 '시도' 부분은 미리 select 한 후
	<select> 태그에 포함싴켜 놓아야 한다.	*/
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/dynamicAddress.do")
	public String address1(Model model) {
		model.addAttribute("sidoLists", dao.selectsido());
		return "address";
	}
	
	/*
	선택된 '시도'를 파라미터로 전달하면 DTO가 받은 후 Mapper를 호출한다. 
	반환된 결과는 List이므로 이를 JSON 객체로 출력하기 위해 Map을 생성한 후 
	result Key에 List를 추가해준다. */
	
	@RequestMapping("/getGugun.do")
	@ResponseBody
	public Map<String, Object> address2(AddressDTO addressDTO) {
		List<AddressDTO> gugunLists = dao.selectGugun(addressDTO);
		Map<String, Object> maps = new HashMap<>();
		maps.put("result", gugunLists);
		return maps;
	}
	
	
}
