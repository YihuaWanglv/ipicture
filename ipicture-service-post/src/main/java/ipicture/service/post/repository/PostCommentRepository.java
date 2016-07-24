
package ipicture.service.post.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ipicture.service.post.model.PostComment;


public interface PostCommentRepository extends PagingAndSortingRepository<PostComment, Long> {

//	PostComment findBySubjectName(String name);
	
	Page<PostComment> findAll(Pageable pageable);
}
