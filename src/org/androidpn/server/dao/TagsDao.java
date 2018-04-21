package org.androidpn.server.dao;

import org.androidpn.server.model.Tags;
import java.util.List;


public interface TagsDao {
	public Tags findByid(long id);
	public void saveTags(Tags tags);
	public void deleteTags(Tags tags);
	public void updateTags(Tags tags);
	public List<Tags> getAllTags();
	public Tags findByName(String tag);
}
