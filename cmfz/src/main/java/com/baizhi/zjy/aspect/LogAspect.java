package com.baizhi.zjy.aspect;

import com.baizhi.zjy.annotation.LogAnnotation;
import com.baizhi.zjy.entity.Admin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    @Autowired
    HttpSession session;

    @Around(value = "@annotation(com.baizhi.zjy.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        //时间  SSS毫秒
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        Date date = new Date();
        String str = sdf.format(date);
        System.out.println("时间："+str);
        //人物
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println("用户："+admin.getUsername());
        //事件
        //获取方法名字
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("方法名："+name);
        //获取自定义直接的值
        //Signature 中保存了该类方法的所有的信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        System.out.println("事件："+annotation.value());
        try {
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("success");
            return proceed;
        } catch (Throwable throwable) {
            System.out.println("error");
            throwable.printStackTrace();
        }
        return null;
    }
}
