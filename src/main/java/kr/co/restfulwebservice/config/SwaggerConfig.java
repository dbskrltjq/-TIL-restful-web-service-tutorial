package kr.co.restfulwebservice.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// 사용자 정보를 나타내기 위한 Contact 객체 생성
	private static final Contact DEFAULT_CONTACT = new Contact("Youna Kim", "http://www.joneconsulting.co.kr", "youna@joneonsulting.co.kr");
	
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title", "My User management REST API service", "1.0", "urn:tos", 
																DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
	// Produce와 Consumer가 어떠한 형태로 사용할 수 있는지 문서타입을 지정
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));	
	// asList() 메서드는 배열 형태의 데이터값을 List 형태로 변환해준다.
	
	@Bean
	public Docket api() {		
		return new Docket(DocumentationType.SWAGGER_2)		// 매개변수로 documentation의 타입을 지정해준다.
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
