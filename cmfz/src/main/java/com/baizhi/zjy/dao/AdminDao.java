package com.baizhi.zjy.dao;

import com.baizhi.zjy.entity.Admin;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDao extends InsertListMapper<Admin>, Mapper<Admin>, DeleteByIdListMapper<Admin,String>{
    //查对象
    Admin queryAdminInfo(String username);
    //查集合 List<Role> queryAllRoles();
}
