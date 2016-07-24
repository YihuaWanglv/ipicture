package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import ipicture.service.post.AppServicePost;
import ipicture.service.post.model.JsonObject;
import ipicture.service.post.model.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppServicePost.class)
//@WebAppConfiguration // 使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
@WebIntegrationTest("server.port:8083")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class SubjectControllerTest {

	private RestTemplate template = new TestRestTemplate();
    @Value("${server.port}")// 注入端口号
    private int port;
    
    private String getBaseUrl() {
		return "http://localhost:" + port;
	}
    
	@Test 
	public void test() {
		
		Subject s = new Subject();
		s.setCreator(1l);
		s.setCreated(new Date());
		s.setSubjectName("test5");
		s.setDescr("test subject");
//		s.setDeleted(0);
		s.setParent_id(0);
		s.setStatus(0);
		s.setType(0);
//		Map<String, Object> p = new HashMap<String,Object>();
//		p.put("subject", s);
		String url = getBaseUrl() + "/subject/save";
		System.err.println("---------------------------" + url);
		JsonObject result = template.postForObject(url, s, JsonObject.class);

		System.err.println("---------------------------");
		
		System.err.println("s=" + result.toString());
		
		System.err.println("---------------------------");
	}
}
