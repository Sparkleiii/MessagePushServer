
package org.androidpn.server.xmpp.handler;

import java.util.List;

import org.androidpn.server.model.Notification;
import org.androidpn.server.service.NotificationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.androidpn.server.xmpp.router.PacketDeliverer;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.androidpn.server.xmpp.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.JID;
import org.xmpp.packet.Packet;
import org.xmpp.packet.PacketError;
import org.xmpp.packet.Presence;


public class PresenceUpdateHandler {

    protected final Log log = LogFactory.getLog(getClass());

    protected SessionManager sessionManager;
    
    private NotificationService notificationService;
    
    private NotificationManager notificationManager;

    /**
     * Constructor.
     */
    public PresenceUpdateHandler() {
        sessionManager = SessionManager.getInstance();
        notificationService = ServiceLocator.getNotificationService();
        notificationManager = new NotificationManager();
    }

    /**
     * Processes the presence packet.
     * 
     * @param 用户在线逻辑判断
     * 
     */
    public void process(Packet packet) {
        ClientSession session = sessionManager.getSession(packet.getFrom());

        try {
            Presence presence = (Presence) packet;
            Presence.Type type = presence.getType();

            if (type == null) { // null代表在线available
                if (session != null
                        && session.getStatus() == Session.STATUS_CLOSED) {
                    log.warn("Rejected available presence: " + presence + " - "
                            + session);
                    return;
                }

                if (session != null) {
                	log.debug("连接"+session.getUsername());
                    session.setPresence(presence);
                    //用户重新上线后发送待发数据，并删除已发送数据
                    if (!session.isInitialized()) {
                        session.setInitialized(true);
                    }
                    List<Notification> nlist = notificationService.findNotificationByUsername(session.getUsername());
                    	for(Notification notification:nlist){
                    		String apiKey = notification.getApiKey();
                    		String title = notification.getTitle();
                    		String message = notification.getMessage();
                    		String uri = notification.getUri(); 
                    		String imageUrl = notification.getImageUrl();
                    		log.debug("发送"+notification.getId());
                    		notificationManager.sendNotifcationToUser(apiKey, session.getUsername(), title, message,uri,imageUrl,false);
                    		notificationService.deleteNotification(notification);
                    	}
                }
                
                
            } else if (Presence.Type.unavailable == type) {

                if (session != null) {
                    session.setPresence(presence);
                }

            } else {
                presence = presence.createCopy();
                if (session != null) {
                    presence.setFrom(new JID(null, session.getServerName(),
                            null, true));
                    presence.setTo(session.getAddress());
                } else {
                    JID sender = presence.getFrom();
                    presence.setFrom(presence.getTo());
                    presence.setTo(sender);
                }
                presence.setError(PacketError.Condition.bad_request);
                PacketDeliverer.deliver(presence);
            }

        } catch (Exception e) {
            log.error("Internal server error. Triggered by packet: " + packet,
                    e);
        }
    }

}
