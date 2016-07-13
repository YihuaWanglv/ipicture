package ipicture.service.user.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ipicture.service.user.dao.UserDao;
import ipicture.service.user.model.User;


@Service
@Transactional
public class UserService {

	
	@Autowired
	private UserDao userMapper;
	
	public List<User> searchAll(){
		List<User> list = userMapper.findAll();
		return list;
	}
}
