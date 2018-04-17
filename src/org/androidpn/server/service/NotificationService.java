package org.androidpn.server.service;

import java.util.List;

import org.androidpn.server.model.Notification;


public interface NotificationService {
	//保存推送消息
		public void saveNotification(Notification notification);
		//根据用户名获取推送消息
		public List<Notification> findNotificationByUsername(String username);
		//删除推送消息
		public void deleteNotification(Notification notification);
		
		public void deleteNotificationByUUID(String uuid);
}
