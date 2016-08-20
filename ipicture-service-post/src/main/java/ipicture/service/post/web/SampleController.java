package ipicture.service.post.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SampleController {

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    @HystrixCommand(fallbackMethod = "fallbackGet")
	public String getSample() {
		
		return "Normal";
	}
	
	public String fallbackGet() {
		return "fallbackGet";
	}
}
