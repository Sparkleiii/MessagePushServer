package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.Notification;


public interface NotificationDao {
	//保存推送消息
	public void saveNotification(Notification notification);
	//根据用户名获取推送消息
	public List<Notification> findNotificationByUsername(String username);
	//删除推送消息
	public void deleteNotification(Notification notification);
	//根据UUID删除推送消息
	public void deleteNotificationByUUID(String uuid);

}
