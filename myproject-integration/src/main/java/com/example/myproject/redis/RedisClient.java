package com.example.myproject.redis;

/**
 * @author kyu.yzf
 * @date 2023/5/18 22:51
 */
public interface RedisClient {

    void addCsrfToken(String key, String value);

    String getCsrfToken(String key);

    void add();

    String get();
}
