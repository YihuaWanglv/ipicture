package ipicture.service.post.model;

import java.io.Serializable;
import java.util.Date;

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
	private Integer sid;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private Date created;
	@Column(nullable = false)
	private Long creator;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Integer type;
	@Column(nullable = false)
	private Date updated;
	@Column(nullable = false)
	private Date last_updated;
	@Column(nullable = false)
	private Long last_updator;
	@Column(nullable = false)
	private Integer deleted;
	@Column(nullable = false)
	private Integer status;
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	public Long getLast_updator() {
		return last_updator;
	}
	public void setLast_updator(Long last_updator) {
		this.last_updator = last_updator;
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
