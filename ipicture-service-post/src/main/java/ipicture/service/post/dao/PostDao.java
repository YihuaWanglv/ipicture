package ipicture.service.post.dao;

import java.util.List;

import ipicture.service.post.model.Post;


public interface PostDao {

	List<Post> findAll();
}
