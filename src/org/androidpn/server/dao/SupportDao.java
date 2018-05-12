package org.androidpn.server.dao;

import org.androidpn.server.model.Support;

import java.util.List;


public interface SupportDao {
	public Support findById(long id);
	public void saveSupport(Support support);
	public void deleteSupport(Support support);
	public boolean isSupport(Support support);
	public int getSupportNumBynotId(Long notId);
}
