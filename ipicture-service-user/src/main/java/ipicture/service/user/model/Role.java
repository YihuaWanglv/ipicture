package ipicture.service.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private int rid;
	@Column(nullable = false)
	private String roleName;
	
	public int getRid() {
		return rid;
	}
	public void setRoleId(int rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
