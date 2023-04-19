package com.example.myproject.impl;

import com.example.myproject.UserService;
import com.example.myproject.mapper.UserMapper;
import com.example.myproject.model.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kyu.yzf
 * @date 2023/4/19 22:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDTO queryByUserID(String userId) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userMapper.selectByPrimaryKey(userId), userDTO);
        return userDTO;
    }
}
