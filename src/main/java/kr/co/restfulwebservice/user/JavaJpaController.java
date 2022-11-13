package kr.co.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/jpa")		// 모든 메소드가 가지고 있을 prefix 값
public class JavaJpaController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")		// 전체 사용자 조회
	public List<UserTest> retrieveAllUsers() {
		return userRepository.findAll();	
	}
	
	@GetMapping("/users/{id}")	// 특정 사용자 조회
	public UserTest retrieveUser(@PathVariable int id) {
		Optional<UserTest> user = userRepository.findById(id);	// 데이터가 검색어에 따라 존재하거나 존재하지 않을 수 있으므로 선택적인 값으로 반환해준다.
		
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		return user.get();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserTest> createUser(@Valid @RequestBody UserTest user) {
		UserTest savedUser = userRepository.save(user);
		
		// 생성된 데이터에 대해 id값을 자동으로 지정
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
