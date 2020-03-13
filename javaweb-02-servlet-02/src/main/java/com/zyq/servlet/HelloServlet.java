package com.zyq.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.getInitParameter()  初始化参数
//        this.getServletConfig()  Servlet配置
//        this.getServletContext() Servlet上下文
        ServletContext servletContext = this.getServletContext();
        String username = "啦啦啦"; // 数据
        servletContext.setAttribute("username", username); // 讲一个数据保存在ServletContext中
        System.out.println("Hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
