package com.baizhi.zjy.dao;

import com.baizhi.zjy.cache.MyBatisCache;
import com.baizhi.zjy.entity.Banner;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
@CacheNamespace(implementation = MyBatisCache.class)
public interface BannerDao extends Mapper<Banner>, DeleteByIdListMapper<Banner,String>{
}
