package com.example.myproject.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author kyu.yzf
 * @date 2023/5/17 22:35
 */
@Service
public class RedisClientImpl implements RedisClient{

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add() {
        Boolean result = redisTemplate.hasKey("my_key");
        if (result) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            String key = "my_key" + System.currentTimeMillis();
            redisTemplate.opsForValue().set(key, System.currentTimeMillis() + "value");
            redisTemplate.expire(key, 10 * 1000, TimeUnit.MILLISECONDS);
        }
        redisTemplate.opsForValue().set("my_key","string_0001");
        redisTemplate.expire("my_key", 30 * 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public String get() {
        Object value = redisTemplate.opsForValue().get("my_key");
        Long time = redisTemplate.getExpire("my_key");
        System.out.println("key的有效期还有" + time + "毫秒");
        System.out.println("value = " + value);
        return (String) value;
    }

}
