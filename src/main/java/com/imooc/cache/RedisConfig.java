package com.imooc.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * RedisConfig  配置类
 *
 * @author yeleichao
 * @date 2018-8-27.
 */
@Configuration
public class RedisConfig {

    @Bean(name="jedisPool")
    public JedisPool getJedisPool(@Value("${redis.host}") String host,@Value("${redis.port}") int port){

        return new JedisPool(host,port);
    }
}
