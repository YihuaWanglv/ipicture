package ipicture.service.post.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post implements Serializable {
	private static final long serialVersionUID = -652402275765647818L;
	@Id
	@GeneratedValue
	private Long pid;
	@Column(nullable = false)
	private String sid;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String created;
	@Column(nullable = false)
	private String creator;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String updated;
	@Column(nullable = false)
	private String last_updated;
	@Column(nullable = false)
	private String last_updator;
	@Column(nullable = false)
	private String deleted;
	@Column(nullable = false)
	private String status;
	
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public String getLast_updator() {
		return last_updator;
	}
	public void setLast_updator(String last_updator) {
		this.last_updator = last_updator;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
