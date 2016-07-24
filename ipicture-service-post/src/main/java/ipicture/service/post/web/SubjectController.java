package ipicture.service.post.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ipicture.service.post.domain.SubjectService;
import ipicture.service.post.exception.BaseException;
import ipicture.service.post.model.JsonObject;
import ipicture.service.post.model.Subject;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired private SubjectService subjectService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list() {
		return "hello!";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<JsonObject> save(@RequestBody Subject subject) throws BaseException {
		if (subject.getDeleted() == null) {
			throw new BaseException("发生错误");
		}
		Subject s = subjectService.save(subject);
		
		return ResponseEntity.accepted().body(new JsonObject(0, "", s));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(Integer sid) {
		subjectService.delete(sid);
	}
}
