package com.baizhi.zjy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Testshiro {

    @Test
    public void testshiro(){
        //1.获取安全管理器工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.通过工厂获取安全管理器
        SecurityManager instance = iniSecurityManagerFactory.createInstance();
        //3.将安全管理器放置于当前shiro环境中
        SecurityUtils.setSecurityManager(instance);
        //4.shiro环境中获取主题对象
        Subject subject = SecurityUtils.getSubject();
        //5.通过前端输入用户名密码  封装令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123456");
        //6.登录
        //注意：shiro的登录状况  由报错信息告知
        // IncorrectCredentialsException : 密码错误
        // UnknownAccountException : 未知账号异常
        subject.login(usernamePasswordToken);
        //subject.isAuthenticated() 判断用户是否登录
        if (subject.isAuthenticated()){
            System.out.println("helloword!");
        }

    }

    @Test
    public void testMD5(){

        //Md5Hash md5Hash = new Md5Hash("123456");
        //System.out.println(md5Hash);



        // md5 加密算法 密码加密|文件对比 MD5序列码
        //Md5Hash md5Hash = new Md5Hash("123456");
        // e10adc3949ba59abbe56e057f20f883e
        //System.out.println(md5Hash);
        // 加盐加密 79c76879ef891e6a7abd0467f9dfb8bf
        //String salt = "uhgq";

       //Md5Hash md5Hash = new Md5Hash("123456" + salt);
//        System.out.println(md5Hash);
        // 先加盐 在散列 0caf568dbf30f5c33a13c56b869259fc
        // 1024 为散列最大值
        Md5Hash abcd = new Md5Hash("zjy", "abcd", 1024);
        System.out.println(abcd);


        //Md5Hash md5Hash = new Md5Hash("admin");
        //System.out.println(md5Hash);

    }



}
