package ipicture.bus.client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
public class SampleController {
	
	@Value("${test.value:notset}")
    private String test;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String hello() {
		return "hello! testValue=" + test;
	}

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
