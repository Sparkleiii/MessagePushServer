package org.androidpn.server.service;

import org.androidpn.server.model.UserTags;
import java.util.List;


public interface UserTagsService {
	public UserTags findByid(long id);
	public void saveUsersTags(UserTags userTags);
	public void deleteUserTags(UserTags userTags);
	public void deleteUserByAccount(String account);
	public void updateUserTags(UserTags userTags);
	public List<String> findByAccount(String account);
	public List<String> findByTag(String tag);
	public List<UserTags> getAllUserTags();
}
