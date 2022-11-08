package kr.co.restfulwebservice.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {	// 예외처리를 하기 위해서 사용되는 자바 객체이다.

	private Date timestamp;		// 예외가 발생한 시간
	private String message;		// 예외 메세지
	private String details;		// 예외 상세정보
}
