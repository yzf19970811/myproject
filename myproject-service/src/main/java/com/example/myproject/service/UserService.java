package com.example.myproject.service;

import com.example.myproject.model.UserDTO;

import java.util.List;

/**
 * @author kyu.yzf
 * @date 2023/4/19 22:05
 */
public interface UserService {

    UserDTO queryByUserID(String UserId);

    void addUserInfo(UserDTO userDTO);

    List<UserDTO> queryUserList();
}
