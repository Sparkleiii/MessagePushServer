package org.androidpn.server.console.controller;

import com.google.gson.Gson;
import org.androidpn.server.model.Comment;
import org.androidpn.server.model.Manager;
import org.androidpn.server.model.NotInformation;
import org.androidpn.server.model.Support;
import org.androidpn.server.service.CommentService;
import org.androidpn.server.service.ManagerService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.service.SupportService;
import org.mortbay.log.Log;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.BinaryOperator;

public class ManagerController extends MultiActionController {
    private ManagerService managerService;

    private CommentService commentService;

    private SupportService supportService;

    private ManagerController(){
        managerService = ServiceLocator.getManagerService();
        commentService = ServiceLocator.getCommentService();
        supportService = ServiceLocator.getSupportService();
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

    //提交评论
    //查询消息的评论
    public void sendComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "success";
        request.setCharacterEncoding("UTF-8");
        Long notId = Long.valueOf(request.getParameter("notId"));
        String account = request.getParameter("account");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        Comment comment = new Comment();
        comment.setAccount(account);
        comment.setContent(content);
        comment.setNotId(notId);
        comment.setTime(time);
        commentService.saveComment(comment);
        sendMessage(jsonData,response);
        Log.debug("sendComment被调用");
    }
    //点赞
    public void support(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "success";
        request.setCharacterEncoding("UTF-8");
        //
        Long notId = Long.valueOf(request.getParameter("notId"));
        String account = request.getParameter("account");
        Support support = new Support();
        support.setAccount(account);
        support.setNotId(notId);
        supportService.saveSupport(support);
        sendMessage(jsonData,response);
        Log.debug("support");
    }
    //取消点赞
    public void cancelSupport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "cancel";
        request.setCharacterEncoding("UTF-8");
        Long notId = Long.valueOf(request.getParameter("notId"));
        String account = request.getParameter("account");
        Support support = new Support();
        support.setAccount(account);
        support.setNotId(notId);
        supportService.deleteSupport(support);
        sendMessage(jsonData,response);
        Log.debug("cancel");
    }
    //获取点赞数
    public void getSupportNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "success";
        request.setCharacterEncoding("UTF-8");
        Long notId = Long.valueOf(request.getParameter("notId"));
        int num = supportService.getSupportNumBynotId(notId);
        Gson gson = new Gson();
        jsonData = gson.toJson(num);
        sendMessage(jsonData,response);
        Log.debug("getSupportNum",num);
    }
    //该用户是否已点赞
    public void isSupport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonData = "no";
        request.setCharacterEncoding("UTF-8");
        Long notId = Long.valueOf(request.getParameter("notId"));
        String account = request.getParameter("account");
        Support support = new Support();
        support.setAccount(account);
        support.setNotId(notId);
        boolean flag = supportService.isSupport(support);
        if(flag){
            jsonData = "yes";
        }
        sendMessage(jsonData,response);
        Log.debug("flag",flag);
    }

    public void sendMessage(Object jsonData,HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonData);
        printWriter.flush();
        printWriter.close();
    }


}
