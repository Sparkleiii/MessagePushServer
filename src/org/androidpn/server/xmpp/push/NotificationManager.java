
package org.androidpn.server.xmpp.push;

import java.util.*;

import org.androidpn.server.model.Login;
import org.androidpn.server.model.Notification;
import org.androidpn.server.model.User;
import org.androidpn.server.model.UserTags;
import org.androidpn.server.service.*;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;


public class NotificationManager {

    private static final String NOTIFICATION_NAMESPACE = "androidpn:iq:notification";

    private final Log log = LogFactory.getLog(getClass());

    private SessionManager sessionManager;
    
    private NotificationService notificationService;

    private UserTagsService userTagsService;
    
    private UserService userService;

    private LoginService loginService;

    /**
     * 构造体
     */
    public NotificationManager() {
        sessionManager = SessionManager.getInstance();
        notificationService = ServiceLocator.getNotificationService();
        userService = ServiceLocator.getUserService();
        userTagsService = ServiceLocator.getUserTagsService();
        loginService = ServiceLocator.getLoginService();
    }

    /**
     * 广播 消息推送所有用户
     * 在线直接推送
     * 推送消息存储在数据库中
     */
    public void sendBroadcast(String apiKey, String title, String message,
            String uri,String imageUrl) {
        log.debug("sendBroadcast()...");
        List<User> ulist = userService.getUsers();
        for(User user:ulist){
        	Random random = new Random();
            String id = Integer.toHexString(random.nextInt());
            saveNotification(apiKey, user.getUsername(),title, message,uri,imageUrl,id);
            IQ notificationIQ = createNotificationIQ(id,apiKey, title, message, uri,imageUrl);
        	ClientSession session = sessionManager.getSession(user.getUsername());
        	if (session!=null&&session.getPresence().isAvailable()) {
                notificationIQ.setTo(session.getAddress());
                session.deliver(notificationIQ); 
            }
        }
    }

    /**
     * 推送消息个特定用户
     */
    public void sendNotifcationToUser(String apiKey, String username,
            String title, String message, String uri,String imageUrl,boolean shouldSave) {
        log.debug("sendNotifcationToUser()...");
        Random random = new Random();
        String id = Integer.toHexString(random.nextInt());
        try {
			User user = userService.getUserByUsername(username);
			if(user!=null ){
				saveNotification(apiKey, username, title, message, uri,imageUrl,id);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
        IQ notificationIQ = createNotificationIQ(id,apiKey, title, message, uri,imageUrl);
        ClientSession session = sessionManager.getSession(username);
        if (session != null) {
            if (session.getPresence().isAvailable()) {
                notificationIQ.setTo(session.getAddress());
                session.deliver(notificationIQ);
            }
        }
    }
    /**
     * 根据别名推送消息
     */
    public void sendNotificationByAlias(String apiKey, String alias,
            String title, String message, String uri,String imageurl,boolean shouldSave){
    	log.debug("NotificationManager sendNotifcationByAlias()...");
    	String username = sessionManager.getUsernameByAlias(alias);
    	if(username != null){
    		sendNotifcationToUser(apiKey, username, title, message, uri, imageurl,shouldSave);
    	}
    }
    /**
     * 根据标签推送消息
     */
    public void sendNotificationByTag(String apiKey, String tag,
            String title, String message, String uri,String imageurl,boolean shouldSave){
    	log.debug("NotificationManager sendNotificationByTag()...");
        String[] tagsArray = tag.split(",");
        List<String> utlist = null;
        Set<String> usernameSet = null;
        for(String tags:tagsArray){
            System.out.println(tags);
            utlist= userTagsService.findByTag(tags);
            usernameSet = new HashSet<String>();
            for(String account:utlist){
                Login login = loginService.getUserByAccount(account);
                usernameSet.add(login.getUsername());
            }
        }
    	if(utlist!=null && !utlist.isEmpty()){
    		for(String username:usernameSet){
    		    System.out.println(username);
    			sendNotifcationToUser(apiKey, username, title, message, uri,imageurl, shouldSave);
    		}
    	}
    }
    
    /**
     * 将推送消息存储到数据库中
     **/
	private void saveNotification(String apiKey, String username,
            String title, String message, String uri,String imageUrl,String uuid){
    	Notification notification=new Notification();
    	notification.setApiKey(apiKey);
    	notification.setTitle(title);
    	notification.setUri(uri);
    	notification.setUsername(username);
    	notification.setMessage(message);
    	notification.setImageUrl(imageUrl);
    	notification.setUuid(uuid);
    	notificationService.saveNotification(notification);
    }
    
    

    /**
     * Creates a new notification IQ and returns it.
     */
    private IQ createNotificationIQ(String id,String apiKey, String title,
            String message, String uri,String imageUrl) {
        // String id = String.valueOf(System.currentTimeMillis());
        Element notification = DocumentHelper.createElement(QName.get(
                "notification", NOTIFICATION_NAMESPACE));
        notification.addElement("id").setText(id);
        notification.addElement("apiKey").setText(apiKey);
        notification.addElement("title").setText(title);
        notification.addElement("message").setText(message);
        notification.addElement("uri").setText(uri);
        notification.addElement("imageUrl").setText(imageUrl);
        
        IQ iq = new IQ();
        iq.setType(IQ.Type.set);
        iq.setChildElement(notification);
        return iq;
    }
}
