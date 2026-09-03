package sukem.vn.services;

import sukem.vn.entity.User;
import sukem.vn.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository = new UserRepository();

	@Override
	public User findById(Long id) {

		return userRepository.findById(id);

	}

	@Override
	public User update(User user) {

		return userRepository.save(user);

	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}