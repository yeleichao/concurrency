package com.imooc.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * RedisClient
 *
 * @author yeleichao
 * @date 2018-8-27.
 */
@Component
public class RedisClient {

    @Resource
    private JedisPool jedisPool;


    public void setValue(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }


    public String getValue(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
