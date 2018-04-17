package org.androidpn.server.xmpp.handler;

import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;
import org.dom4j.Element;
import org.mortbay.log.Log;

public class IQSetAliasHandler extends IQHandler {
	private static final String NAMESPACE = "androidpn:iq:setalias";
	
	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		ClientSession session = sessionManager.getSession(packet.getFrom()); 
		IQ reply;
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
	        		String alias = element.elementText("alias");
	        		if(username != null && !username.equals("") 
	        				&& alias != null && !alias.equals("")){
	        			sessionManager.setUserAlias(username, alias);
	        		}
	        	}
	        }
		return null;
	}

}
