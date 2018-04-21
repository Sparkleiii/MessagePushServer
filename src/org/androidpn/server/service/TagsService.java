package org.androidpn.server.service;

import org.androidpn.server.model.Login;
import org.androidpn.server.model.Tags;

import java.util.List;


public interface TagsService {
	public Tags findByid(long id);
	public void saveTags(Tags tags);
	public void deleteTags(Tags tags);
	public void updateTags(Tags tags);
	public List<Tags> getAllTags();
	public Tags findByName(String tag);
}
