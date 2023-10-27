package login.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import login.test.Service.UserService;
import login.test.dto.UserDTO;
import login.test.model.User.Usertype;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestParam String name, @RequestParam String password) {
	    String jwtToken = this.service.login(name, password);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Bearer " + jwtToken); 

	    Map<String, String> response = new HashMap<>();
	    response.put("token", jwtToken); 

	    return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}


	@GetMapping("/getall")
	@ResponseBody
	public List<UserDTO> getall(HttpServletRequest request) {
		setAll(request);
		return service.getAll();

	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam Long id) {
		request.setAttribute("dto", service.read(id));
		return "user";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam Long id) {
		service.delete(id);
		setAll(request);
		return "user";
	}

	@PostMapping ("/update")	public String update(HttpServletRequest request, @RequestParam Long id , @RequestParam String name , @RequestParam String password, @RequestParam Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName(name);
		String encodedPassword = passwordEncoder.encode(password);
	    dto.setPassword(encodedPassword);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "user";
	}

	@PostMapping("/insert")	
	public String insert(HttpServletRequest request, @RequestParam String name , @RequestParam String password, @RequestParam Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setName(name);
		String encodedPassword = passwordEncoder.encode(password);
	    dto.setPassword(encodedPassword);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "user";


	}
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());

	}

}
