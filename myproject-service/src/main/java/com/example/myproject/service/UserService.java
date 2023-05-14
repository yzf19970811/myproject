package com.example.myproject.service;

import com.example.myproject.model.UserDTO;

/**
 * @author kyu.yzf
 * @date 2023/4/19 22:05
 */
public interface UserService {

    UserDTO queryByUserID(String UserId);

}
