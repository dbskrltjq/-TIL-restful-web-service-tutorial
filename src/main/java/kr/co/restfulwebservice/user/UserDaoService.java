package kr.co.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	private static List<UserTest> users = new ArrayList<>();		// DB용도로 사용
	
	private static int usersCount = 3;
	
	static {
		users.add(new UserTest(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
		users.add(new UserTest(2, "Alice", new Date(), "pass2", "901010-1112222"));
		users.add(new UserTest(3, "Elena", new Date(), "pass3", "801010-2211111"));
	}
	
	public List<UserTest> findAll(){
		return users;
	}
	
	public UserTest findOne(int id) {
		for(UserTest user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public UserTest deleteById(int id) {
		Iterator<UserTest> iterator = users.iterator(); 
		
		while(iterator.hasNext()) {	// 순차적으로 하나씩의 데이터를 가져온다.
			UserTest user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;		// 해당 id가 존재하지 않는 경우
	}
	
	public UserTest updateUser(UserTest user) {
		UserTest existUser = findOne(user.getId());
		
		if(existUser != null) {
			existUser.setName(user.getName());
			existUser.setJoinDate(user.getJoinDate());
			return user;
		}
		
		return null;
	}
	
	public UserTest save(UserTest user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
}
