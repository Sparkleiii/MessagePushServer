package org.androidpn.server.service.impl;

import java.util.List;

import org.androidpn.server.dao.LoginDao;
import org.androidpn.server.model.Login;
import org.androidpn.server.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginServiceImpl implements LoginService{
	protected final Log log = LogFactory.getLog(getClass());

	private LoginDao loginDao;
	
	
	public LoginDao getLoginDao() {
		return loginDao;
	}
	
	public void setLoginDao(LoginDao loginDao){
		this.loginDao = loginDao;
	}

	public void saveUser(Login user) {
		loginDao.saveUser(user);
		
	}

	public void deleteUser(Login user) {
		loginDao.deleteUser(user);
	}

	public void updateUser(Login user) {
		loginDao.updateUser(user);
	}

	public Login getUserByAccount(String account) {
		return loginDao.getUserByAccount(account);
	}

	public Login getUserByClientId(String clientId) {
		return loginDao.getUserByClientId(clientId);
	}

	public List<Login> getAllUsers() {
		return loginDao.getAllUsers();
	}

	public boolean exists(String account) {
		return loginDao.exists(account);
	}


}
