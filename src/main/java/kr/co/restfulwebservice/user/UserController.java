package kr.co.restfulwebservice.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private UserDaoService service;
	// 생성자를 통한 의존성 주입
	public UserController(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")								// 전체 사용자 목록을 반환
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// GET방식, uri: /users/1 or /users/10 -> String
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		User savedUser = service.save(user);
	}
}
