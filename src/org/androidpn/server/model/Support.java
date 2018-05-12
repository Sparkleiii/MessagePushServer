package org.androidpn.server.model;

import javax.persistence.*;

@Entity
@Table(name = "support")
public class Support {
	@Id
	@Column(name="support_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long supportId;

	@Column(name = "not_id",nullable = false , updatable = false)
	private  long notId;

	@Column(name = "account")
	private String account;

	public long getSupportId() {
		return supportId;
	}

	public void setSupportId(long supportId) {
		this.supportId = supportId;
	}

	public long getNotId() {
		return notId;
	}

	public void setNotId(long notId) {
		this.notId = notId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
