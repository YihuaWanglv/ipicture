package ipicture.service.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "hello!";
	}
}
