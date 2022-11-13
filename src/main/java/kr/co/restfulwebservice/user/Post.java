package kr.co.restfulwebservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	
	/**
	 * User : Post -> 1: (0 ~ N), Main : Sub -> Parent : Child, 
	 * @ManyToOne :현재 Post라는 데이터가 여러개가 올수있기때문에 하나의 값과 매칭될 수 있다는 뜻
	 * LAZY : LAZY란 사용자 Entity가 항상 로딩되어있는것이 아니라 요청이 있을시에 로딩하여 가져오겠다는 뜻
	 */
	@ManyToOne(fetch = FetchType.LAZY)		
	@JsonIgnore								
	private UserTest user;
}
