package com.example.exer.sevice;


import com.example.exer.entity.UserInfo;

public interface UserInfoService {
    /**
     * 通过username查找用户信息;
     */
    public UserInfo findByUsername(String username);
}