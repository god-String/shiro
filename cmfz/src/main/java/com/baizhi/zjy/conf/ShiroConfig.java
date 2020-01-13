package com.baizhi.zjy.conf;

import com.baizhi.zjy.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class ShiroConfig {
    @Bean
    //创建过滤器工厂对象
    public ShiroFilterFactoryBean  shiroFilterFactoryBean(){
        //创建过滤器工厂对象
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //过滤器链--》配置哪些需要拦截（认证）|那些不需要拦截（认证）
        /*
        * anon：匿名过滤器  声明的资源可以不认证进行访问
        * authc：认证过滤器  必须认证通过才可以进行访问
        * */

        /*
        *
        * excludePathPatterns("/boot/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/jqgrid/**")
                .excludePathPatterns("/json/**")
                .excludePathPatterns("/login.jsp")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/chu/**")
                .excludePathPatterns("/upload/**");
        *
        * */


        HashMap hashMap = new HashMap();
        hashMap.put("/**","authc");
        //配置匿名资源  -》登录方法
        hashMap.put("/boot/**","anon");
        hashMap.put("/upload/**","anon");
        hashMap.put("/img/**","anon");
        hashMap.put("/jqgrid/**","anon");
        hashMap.put("/json/**","anon");
        hashMap.put("/kindeditor/**","anon");
        hashMap.put("/echarts/**","anon");
        //返回状态码  302表示该资源为认证资源 shiro拦截
        hashMap.put("/admin/login","anon");
        hashMap.put("/admin/createImg","anon");
        //hashMap.put("/com.baizhi.zjy.until.CreateValidateCode","annon");
        //配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        //配置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    //将安全管理器交由工厂管理
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //项目中的安全管理器需要自定义的数据源实现
        CacheManager cacheManager = new EhCacheManager();
        defaultWebSecurityManager.setCacheManager(cacheManager);
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    //将myRealm对象交由工厂管理
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        //自定义Realm需要MD5凭证匹配器支持
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    @Bean
    //讲凭证匹配器交由工厂进行管理
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
}
