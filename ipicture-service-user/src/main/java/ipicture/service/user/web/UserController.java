package ipicture.service.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import ipicture.service.user.model.User;
import ipicture.service.user.repository.UserRepository;

@RestController
public class UserController {

	@Autowired UserRepository userRepository;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "hello!";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> geUsers(){
		return Lists.newArrayList(userRepository.findAll());
	}
}
