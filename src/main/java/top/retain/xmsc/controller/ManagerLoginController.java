package top.retain.xmsc.controller;

import top.retain.xmsc.dto.Manager;
import top.retain.xmsc.service.IManagerService;
import top.retain.xmsc.service.impl.ManagerServiceImpl;
import top.retain.xmsc.util.ResultEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Retain
 * @date 2021/1/12 19:04
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class ManagerLoginController extends HttpServlet {
    IManagerService managerService=new ManagerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        Manager manager = Manager.builder().account(account).pwd(pwd).build();
        if (managerService.login(manager)){
            out.println(ResultEnum.SUCCESS.getCode());
        }else {
            out.println(ResultEnum.ERROR.getCode());
        }
    }
}
