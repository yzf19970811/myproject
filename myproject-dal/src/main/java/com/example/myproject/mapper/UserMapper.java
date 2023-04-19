package com.example.myproject.mapper;

import com.example.myproject.model.UserDO;

/**
 * @author kyu.yzf
 * @date 2023/4/19 21:46
 */
public interface UserMapper {

    UserDO selectByPrimaryKey(String id);

}
