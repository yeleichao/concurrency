package com.imooc.singleton;

import com.imooc.annotation.ThreadSafe;

/**
 * 懒汉式单例模式，线程安全
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@ThreadSafe
public class SingletonExample3 {

    private SingletonExample3(){

    }

    /**单例对象 volatile + 双重检测机制 -> 禁止指令重排*/
    private static volatile SingletonExample3 instance = null;

    public SingletonExample3 getInstance(){
        if(instance == null){
            synchronized (SingletonExample3.class){
                if(instance == null){
                    instance = new SingletonExample3();
                }
            }
            /**上面的一行代码共发生了以下三个步骤
             * 1.memory = allocate() 分配对象的内存空间
             * 2.ctorInstance() 初始化对象
             * 3.instance = memory 设置instance指向刚分配的内存
             * 当线程为1个时，是没有问题的。但是在多线程情况下，会发生问题
             */
        }
        return instance;
    }
}

