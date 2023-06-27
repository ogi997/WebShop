package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.UserDAO;
import net.etfbl.ip.dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 8222837151611765655L;
	
	public UserBean() {}
	
	public List<User> getAllUsers() {
		return UserDAO.selectAll();
	}
	public int addNewUser(User user) {
		return UserDAO.insert(user);
	}
	public User getUserId(Integer id) {
		return UserDAO.getUserById(id);
	}
	public int updateUser(User user) {
		return UserDAO.update(user);
	}
	public int deleteUser(User user) {
		return UserDAO.delete(user);
	}

}
