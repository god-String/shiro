package com.baizhi.zjy.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ChacheAspect {
    @Autowired
    RedisTemplate redisTemplate;

    @Around(value = "@annotation(com.baizhi.zjy.annotation.AddOrSelectCache)")
    public Object addOrSelectChage(ProceedingJoinPoint proceedingJoinPoint){
            //问题：缓存的数据结构如何设计
            //key：原始的类 类的全限定名  key：方法名+参数   value：数据
        String classz = proceedingJoinPoint.getTarget().getClass().toString();
        System.out.println("类的名字："+classz);
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("方法名："+name);
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println("参数名："+args);
        //拼接
        String key=name;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            key+=arg;
        }
        //查询数据库之前  先查询Redis中的缓存数据
        Object o = redisTemplate.opsForHash().get(classz, key);
        //如果查询到直接返回缓存中的数据
        if (o!=null){
            return o;
        }

        //如果查询不到  数据库查询  将数据添加至缓存中
        try {
            Object proceed = proceedingJoinPoint.proceed();
            redisTemplate.opsForHash().put(classz,key,proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    //删除
    @Around(value = "@annotation(com.baizhi.zjy.annotation.ClearCache)")
    public Object clearCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String s = proceedingJoinPoint.getTarget().getClass().toString();
        redisTemplate.delete(s);
        return proceedingJoinPoint.proceed();
    }

}
