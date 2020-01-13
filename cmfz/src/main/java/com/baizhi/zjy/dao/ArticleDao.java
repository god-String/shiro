package com.baizhi.zjy.dao;


import com.baizhi.zjy.cache.MyBatisCache;
import com.baizhi.zjy.entity.Article;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@CacheNamespace(implementation = MyBatisCache.class)
public interface ArticleDao extends Mapper<Article>, InsertListMapper<Article>, DeleteByIdListMapper<Article,String> {
}
