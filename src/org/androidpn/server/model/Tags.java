package org.androidpn.server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tags")
public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "tag", nullable = false, length = 64)
	private String tag;

	public long getid() {
		return id;
	}

	public void setid(long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
