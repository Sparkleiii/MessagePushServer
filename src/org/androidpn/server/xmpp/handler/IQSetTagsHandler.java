package org.androidpn.server.xmpp.handler;

import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.androidpn.server.xmpp.session.SessionManager;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;
import org.dom4j.Element;
import org.mortbay.log.Log;

public class IQSetTagsHandler extends IQHandler {
	private static final String NAMESPACE = "androidpn:iq:settags";
	
	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		 IQ reply = null;
	        ClientSession session = sessionManager.getSession(packet.getFrom());
	        if (session == null) {
	            log.error("Session not found for key " + packet.getFrom());
	            reply = IQ.createResultIQ(packet);
	            reply.setChildElement(packet.getChildElement().createCopy());
	            reply.setError(PacketError.Condition.internal_server_error);
	            return reply;
	        }
	        if(session.getStatus() ==Session.STATUS_AUTHENTICATED){
	        	if(IQ.Type.set.equals(packet.getType())){
	        		Element element = packet.getChildElement();
	        		String username = element.elementText("username");
	        		Log.debug("username:"+username);
	        		String tagsStr = element.elementText("tags");
	        		Log.debug("tags:"+tagsStr);
	        		String[] tagsArray = tagsStr.split(",");
	        		if(tagsArray!=null && tagsArray.length>0){
	        			for(String tag:tagsArray){
		        			sessionManager.setUserTag(username, tag);
		        		}
	        		  log.debug("Set Tag Successfully!!!!!!!!");
	        		}
	        	}
	        }
		return null;
	}

}
