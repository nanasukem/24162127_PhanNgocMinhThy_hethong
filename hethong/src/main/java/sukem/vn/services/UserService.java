package sukem.vn.services;

import sukem.vn.entity.User;

public interface UserService {

	User findById(Long id);

	User update(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean register(String username, String password, String email, String fullname, String phone);

	User login(String username, String password);

}