package org.androidpn.server.model;

import javax.persistence.*;

@Entity
@Table(name = "user_tags")
public class UserTags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="account", nullable = false, length = 64)
    private String account;

    @Column(name = "tag", nullable = false, length = 64)
    private String tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
