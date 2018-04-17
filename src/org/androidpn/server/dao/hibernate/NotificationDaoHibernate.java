package org.androidpn.server.dao.hibernate;

import java.util.List;


import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.androidpn.server.model.User;
import org.androidpn.server.service.UserNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NotificationDaoHibernate extends HibernateDaoSupport implements NotificationDao{
	
	protected final Log log = LogFactory.getLog(getClass());

	public void saveNotification(Notification notification) {
		getHibernateTemplate().saveOrUpdate(notification);
		getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	public List<Notification> findNotificationByUsername(String username) {
		List<Notification> nlist = getHibernateTemplate().find("from Notification where username=?",
				username);
		if (nlist != null && !nlist.isEmpty()) {
			return nlist;
		} else {
			return null;
		}
	}

	public void deleteNotification(Notification notification) {
		if(notification!=null){
			getHibernateTemplate().delete(notification);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Notification findNotificationByUUID(String uuid){
		List<Notification> nlist = getHibernateTemplate().find("from Notification where uuid=?",
				uuid);
		if (nlist != null && !nlist.isEmpty()) {
			return nlist.get(0);
		} else {
			return null;
		}
	}

	public void deleteNotificationByUUID(String uuid) {
		Notification notification = findNotificationByUUID(uuid);
		deleteNotification(notification);
	}

}
