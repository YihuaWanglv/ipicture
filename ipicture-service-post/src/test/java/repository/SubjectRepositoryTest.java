package repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;

import ipicture.service.post.AppServicePost;
import ipicture.service.post.model.Subject;
import ipicture.service.post.repository.SubjectRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppServicePost.class)
@Transactional
public class SubjectRepositoryTest {

	@Autowired SubjectRepository subjectRepository;
	
	@Test
	@Rollback(false)
	public void save() {
		
		Subject s = new Subject();
		s.setCreator(1l);
		s.setCreated(new Date());
		s.setSubjectName("test");
		s.setDescr("test subject");
		
		subjectRepository.save(s);
	}
	
	@Test
	public void getOne() {
		Subject s = subjectRepository.findOne(1);
		System.err.println("--------------------------------------------");
		
		System.err.println("s=" + s.getSubjectName());
		
		System.err.println("--------------------------------------------");
	}
}
