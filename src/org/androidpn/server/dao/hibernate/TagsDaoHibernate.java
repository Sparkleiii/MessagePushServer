package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.TagsDao;
import org.androidpn.server.model.Tags;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class TagsDaoHibernate extends HibernateDaoSupport implements TagsDao {


	@Override
	public Tags findByid(long id) {
		return (Tags) getHibernateTemplate().get(Tags.class,id);
	}

	@Override
	public void saveTags(Tags tags) {
		getHibernateTemplate().saveOrUpdate(tags);
		getHibernateTemplate().flush();
	}

	@Override
	public void deleteTags(Tags tags) {
		getHibernateTemplate().delete(tags);
	}

	@Override
	public void updateTags(Tags tags) {
		getHibernateTemplate().update(tags);
	}

	@Override
	public List<Tags> getAllTags() {
		String hql = "from Tags";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Tags findByName(String tag) {
		String hql = "from Tags where tag = ?";
		List<Tags> tlist = getHibernateTemplate().find(hql,tag);
		if(tlist.size()>0){
			return tlist.get(0);
		}else{
			return null;
		}
	}
}
