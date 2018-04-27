package org.androidpn.server.service;

import org.androidpn.server.model.Manager;

import java.util.List;


public interface ManagerService {
	public Manager findByid(long id);
	public void saveManager(Manager manager);
	public void deleteManager(Manager manager);
	public void updateManager(Manager manager);
	public Manager findByUsername(String username);
	public List<Manager> getAllManagers();
	public boolean exists(String username);
}
