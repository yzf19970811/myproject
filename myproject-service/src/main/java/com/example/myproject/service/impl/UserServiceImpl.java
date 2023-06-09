package com.example.myproject.service.impl;

import com.example.myproject.mapper.UserMapper;
import com.example.myproject.model.UserDO;
import com.example.myproject.model.UserDTO;
import com.example.myproject.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addUserInfo(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(String.valueOf(System.currentTimeMillis()));
        userMapper.insertSelective(userDO);
    }

    @Override
    public List<UserDTO> queryUserList() {
        List<UserDO> userDOList = userMapper.selectAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userDOList.forEach(userDO -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDO, userDTO);
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }
}
