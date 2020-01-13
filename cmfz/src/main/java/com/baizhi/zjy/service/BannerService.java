package com.baizhi.zjy.service;

import com.baizhi.zjy.cache.MyBatisCache;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.Map;

public interface BannerService {
    Map getAllBanners(Integer page, Integer rows);
}
