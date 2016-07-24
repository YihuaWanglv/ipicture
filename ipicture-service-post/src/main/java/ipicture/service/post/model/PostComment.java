package ipicture.service.post.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PostComment implements Serializable {

	private static final long serialVersionUID = -7560899022654807764L;

	@Id
	@GeneratedValue
	private Long cid;
	@Column(nullable = false)
	private Long pid;
	@Column(nullable = false)
	private Date created;
	@Column(nullable = false)
	private Long creator;
	@Column(nullable = false)
	private Date updated;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Integer deleted;
	@Column(nullable = false)
	private Integer status;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
