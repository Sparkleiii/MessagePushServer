
package org.androidpn.server.console.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.androidpn.server.model.NotInformation;
import org.androidpn.server.service.NotInformationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class NotificationController extends MultiActionController {

    private NotificationManager notificationManager;
	private NotInformationService notInformationService;

    public NotificationController() {
        notificationManager = new NotificationManager();
		notInformationService = ServiceLocator.getNotInformationService();
    }

    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // mav.addObject("list", null);
        mav.setViewName("notification/form");
        return mav;
    }

    /**
     * 消息推送Controller
     * 0广播
     * 1用户名
     * 2别名
     * 3标签
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView send(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String broadcast = null;
        String username = null;
        String alias = null;
        String tag =null;
        String title = null;
        String message = null;
        String uri = null;
        String url = null;

        String apiKey = Config.getString("apiKey", "");
        logger.debug("apiKey=" + apiKey);

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for(FileItem item:fileItems){
        	if("broadcast".equals(item.getFieldName())){
        		broadcast = item.getString("utf-8");
        	}else if("username".equals(item.getFieldName())){
        		username = item.getString("utf-8");
        	}else if("alias".equals(item.getFieldName())){
        		alias = item.getString("utf-8");
        	}else if("tag".equals(item.getFieldName())){
        		tag = item.getString("utf-8");
        	}else if("title".equals(item.getFieldName())){
        		title = item.getString("utf-8");
        	}else if("message".equals(item.getFieldName())){
        		message = item.getString("utf-8");
        	}else if("uri".equals(item.getFieldName())){
        		uri = item.getString("utf-8");
        	}else if("image".equals(item.getFieldName())){
				url =uploadImage(request, item);
			}
        }


        if (broadcast.equals("0")) {
            notificationManager.sendBroadcast(apiKey, title, message, uri,url);
        } else if(broadcast.equals("1")){
            notificationManager.sendNotifcationToUser(apiKey, username, title,
                    message, uri,url,true);
        }else if(broadcast.equals("2")){
            notificationManager.sendNotificationByAlias(apiKey, alias, title, message, uri,url, true);
        }else if(broadcast.equals("3")){
        	notificationManager.sendNotificationByTag(apiKey, tag, title, message, uri,url, true);
        }
		NotInformation notInformation = new NotInformation();
        notInformation.setImageUrl(url);
        notInformation.setTitle(title);
        notInformation.setMessage(message);
		String[] tagsArray = tag.split(",");
		if(tagsArray.length>=3){
			notInformation.setTag1(tagsArray[0]);
			notInformation.setTag2(tagsArray[1]);
			notInformation.setTag3(tagsArray[2]);
		}else if(tagsArray.length==2){
            notInformation.setTag1(tagsArray[0]);
            notInformation.setTag2(tagsArray[1]);
		}else if(tagsArray.length==1){
		    notInformation.setTag1(tagsArray[0]);
        }
		notInformationService.saveNotInformation(notInformation);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:notification.do");
        return mav;
    }

    //上传图片
    private String uploadImage(HttpServletRequest request,FileItem fileItem) throws Exception{
    	String uploadPath = getServletContext().getRealPath("/upload");
    	File uploadDir = new File(uploadPath);
    	if(!uploadDir.exists()){
    		uploadDir.mkdir();
    	}
    	if(fileItem!=null && fileItem.getContentType().startsWith("image")){
    		String suffix = fileItem.getName().substring(fileItem.getName().indexOf("."));
    		String fileName = System.currentTimeMillis()+suffix;
    		InputStream is = fileItem.getInputStream();
    		FileOutputStream os = new FileOutputStream(uploadPath + "/" + fileName);
    		byte[] b = new byte[1024];
    		int len=0;
    		while((len = is.read(b))>0){
    			os.write(b,0,len);
    			os.flush();
    		}
    		os.close();
    		is.close();
//    		String serverName = request.getServerName();
    		String serverName = "192.168.1.109";
//			String serverName = "172.25.71.115";
//			String serverName = "192.168.137.206";
//			String serverName = "192.168.43.120";
    		int serverPort = request.getServerPort();
    		String url = "http://"+serverName+":"+serverPort+"/upload/"+fileName;
    		System.out.println("*******************");
    		System.out.println("url"+url);
    		System.out.println("*******************");
    		return url;
    	}
    	return "";
    }
}
