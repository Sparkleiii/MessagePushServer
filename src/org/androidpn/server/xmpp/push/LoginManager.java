
package org.androidpn.server.xmpp.push;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.androidpn.server.model.Notification;
import org.androidpn.server.model.User;
import org.androidpn.server.service.LoginService;
import org.androidpn.server.service.NotificationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.service.UserService;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;


public class LoginManager {

    private static final String NOTIFICATION_NAMESPACE = "androidpn:iq:login";

    private final Log log = LogFactory.getLog(getClass());

    private SessionManager sessionManager;
    
    private LoginService loginService;
    
    private UserService userService;

    /**
     * 构造体
     */
    public LoginManager() {
        sessionManager = SessionManager.getInstance();
        loginService = ServiceLocator.getLoginService();
        userService = ServiceLocator.getUserService();
    }

    /**
     * 返回登录消息
     */
    public void sendLoginToUser(String uid,String uname,String upwd
            ) {
        log.debug("sendNotifcationToUser()...");
        try {
			User user = userService.getUserByUsername(uid);
			if(user!=null ){
				IQ loginIQ = createLoginIQ(uid,uname,upwd);
		        ClientSession session = sessionManager.getSession(uid);
		        if (session != null) {
		            if (session.getPresence().isAvailable()) {
		            	loginIQ.setTo(session.getAddress());
		                session.deliver(loginIQ);
		            }
		        }
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
    }

    /**
     * Creates a new Login IQ and returns it.
     */
    private IQ createLoginIQ(String uid,String uname,String upwd) {
        // String id = String.valueOf(System.currentTimeMillis());
        Element login = DocumentHelper.createElement(QName.get(
                "login", NOTIFICATION_NAMESPACE));
        login.addElement("uid").setText(uid);
        login.addElement("uname").setText(uname);
        login.addElement("upwd").setText(upwd);
        IQ iq = new IQ();
        iq.setType(IQ.Type.get);
        iq.setChildElement(login);
        return iq;
    }
}
