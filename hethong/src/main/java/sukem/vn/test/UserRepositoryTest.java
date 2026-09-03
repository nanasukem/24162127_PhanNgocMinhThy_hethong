package sukem.vn.test;

import sukem.vn.entity.User;
import sukem.vn.repository.UserRepository;

public class UserRepositoryTest {

	public static void main(String[] args) {

		UserRepository repo = new UserRepository();

		User user = repo.findById(1L);

		System.out.println(user.getFullname());

	}

}