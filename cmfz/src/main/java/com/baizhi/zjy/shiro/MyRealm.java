package com.baizhi.zjy.shiro;

import com.baizhi.zjy.dao.AdminDao;
import com.baizhi.zjy.entity.Admin;
import com.baizhi.zjy.until.MyWebWare;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.AuthenticatingRealm;

public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       /* //1.获取用戶名
        String principal = (String) authenticationToken.getPrincipal();
        //2.根据用户名从数据库查询数据
        AdminDao adminDao = (AdminDao) MyWebWare.getBeanByClass(AdminDao.class);
        Admin admin = new Admin();
        admin.setUsername(principal);
        Admin adminByDB = adminDao.selectOne(admin);
        //3.封装AuthenticationInfo信息
        AuthenticationInfo authenticationInfo = new SimpleAccount(adminByDB.getUsername(), adminByDB.getPassword(), this.getName());
        return authenticationInfo;*/
       return null;
    }
}
