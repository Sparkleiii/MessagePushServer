package org.androidpn.server.service.impl;

import org.androidpn.server.dao.ManagerDao;
import org.androidpn.server.model.Manager;
import org.androidpn.server.service.ManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
	protected final Log log = LogFactory.getLog(getClass());

	private ManagerDao managerDao;
	
	
	public ManagerDao getManagerDao() {
		return managerDao;
	}
	
	public void setManagerDao(ManagerDao managerDao){
		this.managerDao = managerDao;
	}


	@Override
	public Manager findByid(long id) {
		return managerDao.findByid(id);
	}

	@Override
	public void saveManager(Manager manager) {
		managerDao.saveManager(manager);
	}

	@Override
	public void deleteManager(Manager manager) {
		managerDao.deleteManager(manager);
	}

	@Override
	public void updateManager(Manager manager) {

	}

	@Override
	public Manager findByUsername(String username) {
		return managerDao.findByUsername(username);
	}

	@Override
	public List<Manager> getAllManagers() {
		return managerDao.getAllManagers();
	}

	@Override
	public boolean exists(String username) {
		return managerDao.exists(username);
	}
}
