
package ipicture.service.user.repository;


import org.springframework.data.repository.CrudRepository;

import ipicture.service.user.model.Role;
import ipicture.service.user.model.User;


public interface RoleRepository extends CrudRepository<Role, Integer> {

}
