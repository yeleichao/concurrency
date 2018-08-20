package com.imooc.singleton;

import com.imooc.annotation.ThreadSafe;

/**
 * 饿汉式单例，线程安全
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }

}
