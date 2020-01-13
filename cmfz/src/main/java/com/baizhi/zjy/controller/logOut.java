package com.baizhi.zjy.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("chu")
public class logOut {
    @RequestMapping("logOut")
    public void logOut(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
        //session.invalidate();
        //session.removeAttribute("admin");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        httpServletResponse.sendRedirect("../login.jsp");
    }
}
