package kr.co.restfulwebservice.user;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@RestController
@RequestMapping("/admin")
public class AdminUserController {
	
	private UserDaoService service;
	// 생성자를 통한 의존성 주입
	public AdminUserController(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")								
	public MappingJacksonValue retrieveAllUsers() {
		
		List<User> users = service.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "joinDate", "password");	// ssn을 제외하고 전달

		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);	// User 클래스의 @JsonFilter("UserInfo") 
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);		// user 데이터값을 전달
		mapping.setFilters(filters);										// filter 적용하기
		
		return mapping;
	}
	
	// GET방식, uri: /users/1 or /users/10 -> String
	@GetMapping("/users/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
												.filterOutAllExcept("id", "name", "joinDate", "ssn");	// password를 제외하고 전달
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);	// User 클래스의 @JsonFilter("UserInfo") 
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);	// user 데이터값을 전달
		mapping.setFilters(filters);										// filter 적용하기
		
		return mapping;
	}

}
