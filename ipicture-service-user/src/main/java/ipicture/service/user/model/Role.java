package ipicture.service.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer rid;
	@Column(nullable = false)
	private String roleName;
	@Column(nullable = false)
	private Integer deleted;
	
	public Integer getRid() {
		return rid;
	}
	public void setRoleId(Integer rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
}
