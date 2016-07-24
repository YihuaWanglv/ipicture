package ipicture.service.post.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ipicture.service.post.dao.PostDao;
import ipicture.service.post.model.Post;
import ipicture.service.post.repository.PostRepository;


@Service
@Transactional
public class PostService {

	
	@Autowired
	private PostDao userMapper;
	
	@Autowired private PostRepository postRepository;
	
	public List<Post> searchAll(){
		List<Post> list = userMapper.findAll();
		return list;
	}
	
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	public void delete(Long id) {
		Post p = postRepository.findOne(id);
		p.setDeleted(1);
	}
}
