package org.androidpn.server.dao.hibernate;

import java.util.List;

import org.androidpn.server.dao.LoginDao;
import org.androidpn.server.model.Login;
import org.androidpn.server.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LoginDaoHibernate extends HibernateDaoSupport implements LoginDao{

	@Override
	public Login findByid(long id) {
		return (Login) getHibernateTemplate().get(Login.class,id);
	}

	public void saveUser(Login user) {
		getHibernateTemplate().save(user);
		getHibernateTemplate().flush();
	}

	public void deleteUser(Login user) {
		getHibernateTemplate().delete(user);
	}

	public void updateUser(Login user) {
		getHibernateTemplate().update(user);
		getHibernateTemplate().flush();
	}

	public Login getUserByAccount(String account) {
		List<Login> list = getHibernateTemplate().find("from Login where account = ?",account);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}

	public Login getUserByClientId(String clientId) {
		List<Login> list = getHibernateTemplate().find(
				"from Login where username =?", clientId);
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	public List<Login> getAllUsers() {
		return getHibernateTemplate().find(
				"from Login u order by u.createdDate desc");
	}

	public boolean exists(String account) {
		Login login = getUserByAccount(account);
		if(login !=null){
			return true;
		}else {
			return false;
		}
	}
}
