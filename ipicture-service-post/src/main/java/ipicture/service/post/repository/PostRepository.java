
package ipicture.service.post.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ipicture.service.post.model.Post;


public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

//	Post findBySubjectName(String name);
	
	Page<Post> findAll(Pageable pageable);
}
