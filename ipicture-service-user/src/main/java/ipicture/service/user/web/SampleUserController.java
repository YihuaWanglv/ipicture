package ipicture.service.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SampleUserController {

	@RequestMapping(value = "/usersample", method = RequestMethod.GET, produces = "application/json")
    @HystrixCommand(fallbackMethod = "fallbackGet")
	public String getUserSample() {
		throw new RuntimeException();
//		return "Normal";
	}
	
	public String fallbackGet() {
		return "fallbackGet";
	}
}
