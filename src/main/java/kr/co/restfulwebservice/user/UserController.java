package kr.co.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserController {
	
	private UserDaoService service;
	// 생성자를 통한 의존성 주입
	public UserController(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")								// 전체 사용자 목록을 반환
	public List<UserTest> retrieveAllUsers(){
		return service.findAll();
	}
	
	// GET방식, uri: /users/1 or /users/10 -> String
	@GetMapping("/users/{id}")
	public UserTest retrieveUser(@PathVariable int id) {
		UserTest user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserTest> createUser(@Valid @RequestBody UserTest user) {
		UserTest savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()		// 사용자 요청 uri
								.path("/{id}")								// buildAndExpand를 통해 얻는 값이 들어옴
								.buildAndExpand(savedUser.getId())
								.toUri();									// uri 생성
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		UserTest user = service.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("[%s]의 사용자가 존재하지 않습니다.", id));
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<Object> updateUser(@RequestBody UserTest user) {
		UserTest existUser = service.updateUser(user);
		
		if(existUser == null) {
			throw new UserNotFoundException(String.format("[%s]의 사용자가 존재하지 않습니다.", user.getId()));
		}
		
		return ResponseEntity.noContent().build();
	}
}
