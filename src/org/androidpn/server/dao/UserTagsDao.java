package org.androidpn.server.dao;

import org.androidpn.server.model.UserTags;

import java.util.List;


public interface UserTagsDao {
	public UserTags findByid(long id);
	public void deleteUserTags(UserTags userTags);
	public void updateUserTags(UserTags userTags);
	public List<String> findByAccount(String account);
	public List<String> findByTag(String tag);
	public List<UserTags> getAllUserTags();
	public void saveUsersTags(UserTags userTags);
}
