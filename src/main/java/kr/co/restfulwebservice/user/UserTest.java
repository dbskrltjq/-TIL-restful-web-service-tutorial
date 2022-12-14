package kr.co.restfulwebservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class UserTest {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
	@ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
	private String name;
	@Past						// 현재 회원이 가입하는 날짜는 미래 날짜 사용 불가
	@ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요.")
	private Date joinDate;
	
	@ApiModelProperty(notes = "사용자 패스워드를 입력해 주세요.")
	private String password;
	@ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
	private String ssn;			// 주민등록번호
	
	@OneToMany(mappedBy = "user")		// Post 클래스의 사용자 필드명
	private List<Post> posts;

	public UserTest(Integer id, @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.") String name, @Past Date joinDate,
			String password, String ssn) {
		super();
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.password = password;
		this.ssn = ssn;
	}
	
	
}
