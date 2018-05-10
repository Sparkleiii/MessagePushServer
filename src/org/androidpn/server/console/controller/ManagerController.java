package org.androidpn.server.console.controller;

import com.google.gson.Gson;
import org.androidpn.server.model.Comment;
import org.androidpn.server.model.Manager;
import org.androidpn.server.model.NotInformation;
import org.androidpn.server.service.CommentService;
import org.androidpn.server.service.ManagerService;
import org.androidpn.server.service.ServiceLocator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManagerController extends MultiActionController {
    private ManagerService managerService;

    private CommentService commentService;

    private ManagerController(){
        managerService = ServiceLocator.getManagerService();
        commentService = ServiceLocator.getCommentService();
    }

    public ModelAndView toLogin(HttpServletRequest request,
                                HttpServletResponse response,
                                ModelAndView modelAndView){
        String error = "用户名/密码错误";
        modelAndView.addObject("error",error);
        modelAndView.setViewName("manager/login");
        return modelAndView;
    }

    //登陆验证
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response,
                              ModelAndView modelAndView){
        String username  = request.getParameter("username");
        String password = request.getParameter("password");
        Manager manager = managerService.findByUsername(username);
        modelAndView.setViewName("redirect:manager.do?action=toLogin");
        if (manager != null) {
            if(manager.getPassword().equals(password)){
                modelAndView.setViewName("index");
            }
        }
        return modelAndView;
    }

    //查询消息的评论
    public void getCommentByNotId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "error";
        request.setCharacterEncoding("UTF-8");
        Long notId = Long.valueOf(request.getParameter("notId"));
        List<Comment> commentListlist = commentService.findCommentByNotID(notId);
        Gson gson = new Gson();
        jsonData = gson.toJson(commentListlist);
        System.out.println(jsonData);
        sendMessage(jsonData,response);
    }

    public void sendMessage(Object jsonData,HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonData);
        printWriter.flush();
        printWriter.close();
    }


}
