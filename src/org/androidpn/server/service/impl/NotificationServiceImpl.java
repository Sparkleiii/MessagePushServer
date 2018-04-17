package org.androidpn.server.service.impl;

import java.util.List;
import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.androidpn.server.service.NotificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NotificationServiceImpl implements NotificationService{
	protected final Log log = LogFactory.getLog(getClass());
	
	private NotificationDao notificationDao;
	
	public void setNotificationDao(NotificationDao notificationDao){
		this.notificationDao = notificationDao;
	}

	public void saveNotification(Notification notification) {
		notificationDao.saveNotification(notification);
		
	}

	public List<Notification> findNotificationByUsername(String username) {
		return notificationDao.findNotificationByUsername(username);
	}

	public void deleteNotification(Notification notification) {
		notificationDao.deleteNotification(notification);
	}

	public void deleteNotificationByUUID(String uuid) {
		notificationDao.deleteNotificationByUUID(uuid);
	}
	
	

	

}
