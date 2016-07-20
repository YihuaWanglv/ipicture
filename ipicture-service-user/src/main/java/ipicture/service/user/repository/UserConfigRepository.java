
package ipicture.service.user.repository;


import org.springframework.data.repository.CrudRepository;

import ipicture.service.user.model.Role;


public interface UserConfigRepository extends CrudRepository<Role, Integer> {

}
