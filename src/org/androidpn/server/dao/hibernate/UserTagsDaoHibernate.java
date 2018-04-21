package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.UserTagsDao;
import org.androidpn.server.model.UserTags;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class UserTagsDaoHibernate extends HibernateDaoSupport implements UserTagsDao {

	@Override
	public UserTags findByid(long id) {
		return (UserTags) getHibernateTemplate().get(UserTags.class,id);
	}

	@Override
	public void deleteUserTags(UserTags userTags) {
		getHibernateTemplate().delete(userTags);
	}

	@Override
	public void updateUserTags(UserTags userTags) {
		getHibernateTemplate().update(userTags);
	}

	@Override
	public void saveUsersTags(UserTags userTags){
		getHibernateTemplate().save(userTags);
	}

	@Override
	public List<String> findByAccount(String account) {
		String hql = "select distinct(tag) from UserTags where account = ?";
		return getHibernateTemplate().find(hql,account);
	}

	@Override
	public List<String> findByTag(String tag) {
		String hql = "select distinct(account) from UserTags where tag = ?";
		return getHibernateTemplate().find(hql,tag);
	}


	@Override
	public List<UserTags> getAllUserTags() {
		String hql = "from UserTags";
		return getHibernateTemplate().find(hql);
	}
}
