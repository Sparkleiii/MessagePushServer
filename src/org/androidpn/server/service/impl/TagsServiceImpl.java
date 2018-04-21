package org.androidpn.server.service.impl;

import org.androidpn.server.dao.LoginDao;
import org.androidpn.server.dao.TagsDao;
import org.androidpn.server.dao.hibernate.TagsDaoHibernate;
import org.androidpn.server.model.Login;
import org.androidpn.server.model.Tags;
import org.androidpn.server.service.LoginService;
import org.androidpn.server.service.TagsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class TagsServiceImpl implements TagsService {
	protected final Log log = LogFactory.getLog(getClass());
	private TagsDao tagsDao;

	public TagsDao getTagsDao() {
		return tagsDao;
	}
	public void setTagsDao(TagsDao tagsDao) {
		this.tagsDao = tagsDao;
	}

	@Override
	public Tags findByid(long id) {
		return tagsDao.findByid(id);
	}

	@Override
	public void saveTags(Tags tags) {
		tagsDao.saveTags(tags);
	}

	@Override
	public void deleteTags(Tags tags) {
		tagsDao.deleteTags(tags);
	}

	@Override
	public void updateTags(Tags tags) {
		tagsDao.updateTags(tags);
	}

	@Override
	public List<Tags> getAllTags() {
		return tagsDao.getAllTags();
	}

	@Override
	public Tags findByName(String tag) {
		return tagsDao.findByName(tag);
	}


}
