
package ipicture.service.post.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ipicture.service.post.model.Subject;


public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {

	Subject findBySubjectName(String name);
	
	Page<Subject> findAll(Pageable pageable);
}
