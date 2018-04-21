package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.Login;


public interface LoginDao {
	public Login findByid(long id);
	public void saveUser(Login user);
	public void deleteUser(Login user);
	public void updateUser(Login user);
	public Login getUserByAccount(String account);
	public Login getUserByClientId(String clientId);
	public List<Login> getAllUsers();
	public boolean exists(String account);
}
