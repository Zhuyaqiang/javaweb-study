package com.zyq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// 保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 服务器, 把用户上次访问的时间封装成信件
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        // Cookie, 服务器从客户端获取
        Cookie[] cookies = req.getCookies(); // 说明cookie可能存在多个
        // 判断cookie是否存在
        if (cookies != null) {
            // 如果存在
            out.write("你上一次访问的时间是: ");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")) {
                    // 获取cookie中的值
                    long l = Long.parseLong(cookie.getValue());
                    Date date = new Date(l);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
                    out.write(simpleDateFormat.format(date));
                }
            }
        } else {
            out.write("这是您第一次访问本站");
        }

        // 服务器给客户端相应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        // cookie有效期为一天
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
