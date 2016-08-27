package ipicture.service.post.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ipicture.service.post.domain.PostService;
import ipicture.service.post.model.JsonObject;
import ipicture.service.post.model.Post;

@RestController
//@RequestMapping("/")
public class PostController {

	@Autowired private PostService postService;
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String hello() {
		return "hello!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject save(Post post) {
		postService.save(post);
		
		return new JsonObject();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject add(@RequestParam String title, @RequestParam String content, @RequestParam Long creator
			, @RequestParam Integer sid, @RequestParam Integer type, @RequestParam Integer status, @RequestParam Integer delete) {
		Assert.noNullElements(new Object[]{title , sid, creator, content}, "title, content, creator and subjectId must not be null.");
		Post p = new Post();
		p.setTitle(title);
		p.setContent(content);
		p.setCreator(creator);
		p.setSid(sid);
		p.setCreated(new Date());
		p.setStatus(status == null ? 0 : status);
		p.setType(type == null ? 0 : type);
		p.setDeleted(delete == null ? 0 : delete);
		p.setUpdated(new Date());
		postService.save(p);
		return new JsonObject();
	}
}
