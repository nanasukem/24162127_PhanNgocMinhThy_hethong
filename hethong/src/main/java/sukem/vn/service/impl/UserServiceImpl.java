package sukem.vn.service.impl;

import sukem.vn.dao.UserDao;
import sukem.vn.dao.impl.UserDaoImpl;
import sukem.vn.model.User;
import sukem.vn.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new User(email, username, fullname, password, null, 5, phone, date));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean update(User user) {
		// Ủy quyền gọi hàm update xử lý JPA dưới UserDaoImpl
		return userDao.update(user);
	}
}