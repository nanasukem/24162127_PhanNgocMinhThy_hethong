package sukem.vn.test;

import sukem.vn.entity.User;
import sukem.vn.services.UserService;
import sukem.vn.services.UserServiceImpl;

public class UserServiceTest {

	public static void main(String[] args) {

		UserService service = new UserServiceImpl();

		User user = service.findById(1L);

		System.out.println(user.getFullname());

	}

}