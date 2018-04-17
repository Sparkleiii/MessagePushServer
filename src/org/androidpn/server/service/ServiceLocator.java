
package org.androidpn.server.service;

import org.androidpn.server.xmpp.XmppServer;

public class ServiceLocator {

    public static String USER_SERVICE = "userService";
    
    public static String NOTIFICATION_SERVICE = "notificationService";
    
    public static String LOGIN_SERVICE = "loginService";

    /**
     * Generic method to obtain a service object for a given name. 
     * 
     * @param name the service bean name
     * @return
     */
    public static Object getService(String name) {
        return XmppServer.getInstance().getBean(name);
    }
    public static LoginService getLoginService(){
    	return (LoginService) XmppServer.getInstance().getBean(LOGIN_SERVICE);
    }


    public static UserService getUserService() {
        return (UserService) XmppServer.getInstance().getBean(USER_SERVICE);
    }
    
    public static NotificationService getNotificationService(){
    	return (NotificationService)XmppServer.getInstance().getBean(NOTIFICATION_SERVICE);
    }

}
