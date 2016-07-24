package controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import ipicture.service.post.AppServicePost;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppServicePost.class)
@WebIntegrationTest("server.port:8083")
public class BaseControllerTest {

	protected RestTemplate template = new TestRestTemplate();
	
    @Value("${server.port}")// 注入端口号
    private int port;
    
    protected String getBaseUrl() {
		return "http://localhost:" + port;
	}
}
