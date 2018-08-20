package com.imooc.singleton;

import com.imooc.annotation.Recommend;
import com.imooc.annotation.ThreadSafe;

/**
 * 使用枚举单例
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@ThreadSafe
@Recommend
public class SingletonExample4 {
    private SingletonExample4(){

    }

    public static SingletonExample4 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample4 singleton;

        //JVM保证这个方法绝对只对调用一次
        Singleton(){
            singleton = new SingletonExample4();
        }

        public SingletonExample4 getInstance(){
            return singleton;
        }
    }
}
