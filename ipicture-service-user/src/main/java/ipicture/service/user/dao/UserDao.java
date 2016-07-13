package ipicture.service.user.dao;

import java.util.List;

import ipicture.service.user.model.User;


public interface UserDao {

	List<User> findAll();
}
