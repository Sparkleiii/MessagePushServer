
package org.androidpn.server.console.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.androidpn.server.model.Login;
import org.androidpn.server.model.User;
import org.androidpn.server.service.LoginService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.service.UserService;
import org.androidpn.server.xmpp.presence.PresenceManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {

    private UserService userService;
    private LoginService loginService;

    public UserController() {
        userService = ServiceLocator.getUserService();
        loginService = ServiceLocator.getLoginService();
    }

    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        PresenceManager presenceManager = new PresenceManager();
        List<User> userList = userService.getUsers();
        for (User user : userList) {
            if (presenceManager.isAvailable(user)) {
                // Presence presence = presenceManager.getPresence(user);
                user.setOnline(true);
            } else {
                user.setOnline(false);
            }
            // logger.debug("user.online=" + user.isOnline());
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("userList", userList);
        mav.setViewName("user/list");
        return mav;
    }
    

    public void register(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        String jsonData = "error";
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        String clientId = request.getParameter("clientId");
        //若该用户名已存在
        if(!loginService.exists(account)){
            Login exist = loginService.getUserByClientId(clientId);
            if(exist!=null){
                exist.setUsername("");
                loginService.updateUser(exist);
            }
            Login login = new Login();
            login.setUsername(clientId);
            login.setAccount(account);
            login.setPassword(password);
            login.setCreatedDate(new Date());
            loginService.saveUser(login);
            System.out.println("注册成功");
            jsonData = "success";
        }else {
            jsonData = "exist";
            System.out.println("用户名已存在");
        }
        sendMessage(jsonData,response);
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String jsonData =  "error";
    	String account = request.getParameter("username");
    	String password = request.getParameter("password");
    	String clientId = request.getParameter("clientId");
    	//验证用户是否存在
    	if(loginService.exists(account)){
    		Login login = loginService.getUserByAccount(account);
    		if(login.getPassword().equals(password)){
    			jsonData = "success";
    			//处理绑定的客户端
    			//若客户端编号与原login不匹配，解绑重新绑定
    			if(!login.getUsername().equals(clientId)){
    				try {
						userService.deleteUser(login.getUsername());
    					login.setUsername(clientId);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println();
					}
    			}
    		}
    	}
        sendMessage(jsonData,response);
    }

    public void sendMessage(String jsonData,HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonData);
        printWriter.flush();
        printWriter.close();
    }


    
    

}
