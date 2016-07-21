package ipicture.service.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserConfig implements Serializable {

	private static final long serialVersionUID = -4683278640778658076L;
	
	@Id
	@GeneratedValue
	private Long ucid;
	@Column()
	private String detail;
	
	public Long getUcid() {
		return ucid;
	}
	public void setUcid(Long ucid) {
		this.ucid = ucid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
