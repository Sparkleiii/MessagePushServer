package org.androidpn.server.service.impl;

import org.androidpn.server.dao.TagsDao;
import org.androidpn.server.dao.UserTagsDao;
import org.androidpn.server.model.Tags;
import org.androidpn.server.model.UserTags;
import org.androidpn.server.service.TagsService;
import org.androidpn.server.service.UserTagsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class UserTagsServiceImpl implements UserTagsService {
	protected final Log log = LogFactory.getLog(getClass());
	private UserTagsDao userTagsDao;
	public UserTagsDao getUserTagsDao() {
		return userTagsDao;
	}
	public void setUserTagsDao(UserTagsDao userTagsDao){
		this.userTagsDao = userTagsDao;
	}

	@Override
	public UserTags findByid(long id) {
		return userTagsDao.findByid(id);
	}

	@Override
	public void saveUsersTags(UserTags userTags) {
		userTagsDao.saveUsersTags(userTags);
	}

	@Override
	public void deleteUserTags(UserTags userTags) {
		userTagsDao.deleteUserTags(userTags);
	}

	@Override
	public void updateUserTags(UserTags userTags) {
		userTagsDao.updateUserTags(userTags);
	}

	@Override
	public List<String> findByAccount(String account) {
		return userTagsDao.findByAccount(account);
	}

	@Override
	public List<String> findByTag(String tag) {
		return userTagsDao.findByTag(tag);
	}

	@Override
	public List<UserTags> getAllUserTags() {
		return userTagsDao.getAllUserTags();
	}
}
