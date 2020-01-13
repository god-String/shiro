package com.baizhi.zjy.cache;

import com.baizhi.zjy.until.MyWebWare;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MyBatisCache implements Cache {
    //必须有一个ID属性
    private final String id;
    //RedisTemplate redisTemplate

    //必须有一个id属性的有参构造
    public MyBatisCache(String id){
        this.id=id;
    }
    @Override
    public String getId() {
        return this.id;
    }
    //添加缓存 2
    @Override
    public void putObject(Object key, Object value) {
        //通过工具类获取RedisTemplate对象
        RedisTemplate redisTemplate = (RedisTemplate) MyWebWare.getBeanByName("redisTemplate");
        //通过RedisTemplate操作Redis
        //注意：存储时是用hash类型存储数据，方便数据更改时删除当前namespace空间的所有数据
        redisTemplate.opsForHash().put(this.id,key.toString(),value);

    }
    //取数据
    @Override
    public Object getObject(Object key) {
        //通过工具类获取RedisTemplate对象
        RedisTemplate redisTemplate = (RedisTemplate) MyWebWare.getBeanByName("redisTemplate");
        Object o = redisTemplate.opsForHash().get(this.id, key.toString());
        return o;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }
    //删除缓存
    @Override
    public void clear() {
        //通过工具类获取RedisTemplate对象
        RedisTemplate redisTemplate = (RedisTemplate) MyWebWare.getBeanByName("redisTemplate");
        redisTemplate.delete(this.id);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
