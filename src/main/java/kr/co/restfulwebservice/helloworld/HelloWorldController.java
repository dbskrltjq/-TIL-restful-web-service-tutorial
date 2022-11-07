package kr.co.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	// 메소드방식 -> GET
	// uri지정 -> /hello-world  (endpoint)
	// @RequestMapping()을 사용한다면 @RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping("/hello-world")		// url만 지정하면 속성(path)을 적지 않아도 되지만, 다른 값들도 지정하면 어떤 속성인지 명확하게 지정해줘야한다.
	public String helloWorld() {
		return "Hello World";		// 단순한 문자열 형태의 반환값
	}
	
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");	// 자바 빈 형태의 반환값
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) { 			//@PathVariable : 해당 변수는 가변 데이터로 사용될 것이라는 의미
		return new HelloWorldBean(String.format("Hello World, %s", name));		// 다른 변수 이름을 사용한다면 @PathVariable(value="uri에 있는 가변변수명")
		
	}
}
