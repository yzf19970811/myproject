package com.example.myproject.mapper;

import com.example.myproject.model.UserDO;

import java.util.List;

/**
 * @author kyu.yzf
 * @date 2023/4/19 21:46
 */
public interface UserMapper {

    UserDO selectByPrimaryKey(String id);

    void insertSelective(UserDO userDO);

    List<UserDO> selectAll();
}
