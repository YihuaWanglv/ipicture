package ipicture.service.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppServicePost {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(AppServicePost.class, args);
    }
	
}
