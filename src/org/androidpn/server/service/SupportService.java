package org.androidpn.server.service;

import org.androidpn.server.model.Support;


public interface SupportService {
	public Support findById(long id);
	public void saveSupport(Support support);
	public void deleteSupport(Support support);
	public boolean isSupport(Support support);
	public int getSupportNumBynotId(Long notId);
}
