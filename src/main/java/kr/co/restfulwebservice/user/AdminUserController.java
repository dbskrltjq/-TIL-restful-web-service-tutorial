package kr.co.restfulwebservice.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
@RequestMapping("/admin")		// prefix 설정
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
	
	// GET방식, uri: /admin/users/1 -> /admin/v1/users/1 
	@GetMapping("/v1/users/{id}")
	public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
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
	
	@GetMapping("/v2/users/{id}")	// V2
	public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		// User -> UserV2 
		UserV2 userV2 = new UserV2();
		BeanUtils.copyProperties(user, userV2);		// spring framework에서 제공해주는 util 클래스. 
													// 두 인스턴스 간의 공통적인 필드(프로퍼티)가 있을 경우에 copy해주는 기능
		userV2.setGrade("VIP");						// UserV2에서 관리하고자 하는 데이터는 사용자 등급 
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "joinDate", "grade");	// grade도 전달
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);	// UserV2 클래스의 @JsonFilter("UserInfoV2") 
		
		MappingJacksonValue mapping = new MappingJacksonValue(userV2);	// userV2 데이터값을 전달
		mapping.setFilters(filters);										// filter 적용하기
		
		return mapping;
	}

}
