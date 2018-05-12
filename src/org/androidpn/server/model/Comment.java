package org.androidpn.server.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentId;

	@Column(name = "not_id",updatable = false)
	private  long notId;

	@Column(name="content",nullable = false)
	private String content;

	@Column(name = "account")
	private String account;

	@Column(name = "time",length = 16)
	private String time;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getNotId() {
		return notId;
	}

	public void setNotId(long notId) {
		this.notId = notId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
