package ipicture.service.user.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ipicture.service.user.dao.PostDao;
import ipicture.service.user.model.Post;


@Service
@Transactional
public class PostService {

	
	@Autowired
	private PostDao userMapper;
	
	public List<Post> searchAll(){
		List<Post> list = userMapper.findAll();
		return list;
	}
}
