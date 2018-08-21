package com.imooc.immutable;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 不可变对象
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@Slf4j
public class ImmutableExample {

    private  static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        /**Collections.unmodifiableMap(map) 表示map里面的内容不可变*/
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //map.put(3,4);
        map = Maps.newHashMap();
        log.info("{}",map);
    }

}
