package ipicture.service.user.dao;

import java.util.List;

import ipicture.service.user.model.Post;


public interface PostDao {

	List<Post> findAll();
}
