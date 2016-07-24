package ipicture.service.post.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ipicture.service.post.model.Subject;
import ipicture.service.post.repository.SubjectRepository;

@Service
@Transactional
public class SubjectService {

	@Autowired private SubjectRepository subjectRepository;
	
	@Transactional
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	public void delete(Integer id) {
		Subject s = subjectRepository.findOne(id);
		s.setDeleted(1);
	}
	
	public Page<Subject> list(Pageable pageable) {
		return subjectRepository.findAll(pageable);
	}
	
}
