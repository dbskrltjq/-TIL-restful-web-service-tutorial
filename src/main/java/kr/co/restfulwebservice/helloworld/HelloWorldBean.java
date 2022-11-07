package kr.co.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor		// 매개변수가 없는 디폴트 생성자
public class HelloWorldBean {
	private String message;
	
	

}
