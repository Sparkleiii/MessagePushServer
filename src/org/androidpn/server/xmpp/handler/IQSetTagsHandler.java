package org.androidpn.server.xmpp.handler;

import org.androidpn.server.model.Tags;
import org.androidpn.server.model.UserTags;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.service.TagsService;
import org.androidpn.server.service.UserTagsService;
import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;
import org.dom4j.Element;
import org.mortbay.log.Log;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class IQSetTagsHandler extends IQHandler {
	private static final String NAMESPACE = "androidpn:iq:settags";
	private TagsService tagsService;
	private UserTagsService userTagsService;

	@Override
	public String getNamespace() {
		tagsService = ServiceLocator.getTagsService();
		userTagsService = ServiceLocator.getUserTagsService();
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
	        		String tagsStr = element.elementText("tags");
	        		System.out.println("username"+username);
	        		String[] tagsArray = tagsStr.split(",");
	        		userTagsService.deleteUserByAccount(username);
					log.debug("Delete old Tag Successfully!!!!!!!!");
	        		UserTags userTags = new UserTags();
					userTags.setAccount(username);
	        		if(tagsArray!=null && tagsArray.length>0){
	        			for(String tag:tagsArray){
							try {
								tag = new String(tag.getBytes("utf-8"),"utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							System.out.println(tag);
	        				//若在标签数据库中不存在该标签，则将之加入数据库
							if(tagsService.findByName(tag)==null){
								Tags tags = new Tags();
								tags.setTag(tag);
								tagsService.saveTags(tags);
							}
							//若userTags已存在，则不再添加
							//删除之前添加的标签
							userTags.setTag(tag);
							System.out.println(userTagsService);
							System.out.println("account="+userTags.getAccount());
							System.out.println("tag="+userTags.getTag());
							userTagsService.saveUsersTags(userTags);
		        		}
	        		  log.debug("Set Tag Successfully!!!!!!!!");
	        		}
	        	}
	        }
		return null;
	}

}
