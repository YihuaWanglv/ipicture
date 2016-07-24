package controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.ImmutableMap;

import ipicture.service.post.model.JsonObject;

public class PostControllerTest extends BaseControllerTest {

	
	@Test
	public void addList() {
		
		String url = getBaseUrl() + "/post/add";
		for (int i = 0; i < 5; i++) {
			int type = i%4;
			int status = i%3;
			int delete = i%2;
			Map<String, Object> m = ImmutableMap.<String, Object>builder().put("title", "post-title-" + i).put("content", "post-content-" + i)
				.put("sid", 1).put("creator", 1l).put("type", type).put("status", status).put("delete", delete).build();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "post-title-" + i);
			map.put("content", "post-content-" + i);
			map.put("sid", 1);
			map.put("creator", 1l);
			map.put("type", type);
			map.put("status", status);
			map.put("delete", delete);
			JsonObject obj = template.postForObject(url, map, JsonObject.class);
			if (obj != null) {
				System.err.println("status=" + obj.getStatus() + ", message=" + obj.getMessage() + ", data=" + obj.getData());
				
//				System.err.println("status=" + obj.getStatusCode() + ", message=" + obj.getBody());
			}
			
		}
	}
}
