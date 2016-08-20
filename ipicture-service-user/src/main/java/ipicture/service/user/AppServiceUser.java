package ipicture.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient
public class AppServiceUser {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(AppServiceUser.class, args);
    }
	
}
