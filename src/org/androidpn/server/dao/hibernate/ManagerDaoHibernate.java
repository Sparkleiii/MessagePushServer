package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.ManagerDao;
import org.androidpn.server.model.Manager;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class ManagerDaoHibernate extends HibernateDaoSupport implements ManagerDao {

	@Override
	public Manager findByid(long id) {
		return (Manager) getHibernateTemplate().get(Manager.class,id);
	}

	public void saveManager(Manager manager) {
		getHibernateTemplate().saveOrUpdate(manager);
		getHibernateTemplate().flush();
	}

	public void deleteManager(Manager manager) {
		getHibernateTemplate().delete(manager);
	}

	public void updateManager(Manager manager) {
		getHibernateTemplate().update(manager);
	}

	public Manager findByUsername(String username) {
		List<Manager> list = getHibernateTemplate().find("from Manager where username = ?",username);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}


	public List<Manager> getAllManagers() {
		return getHibernateTemplate().find("from Manager");
	}

	public boolean exists(String account) {
		Manager manager = findByUsername(account);
		if(manager !=null){
			return true;
		}else {
			return false;
		}
	}
}
