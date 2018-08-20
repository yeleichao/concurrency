package com.imooc.singleton;

import com.imooc.annotation.NotThreadSafe;

/**
 * 懒汉式单例
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@NotThreadSafe
public class SingletonExample {

    private SingletonExample(){

    }

    private static SingletonExample instance = null;



    public SingletonExample getInstance(){
        if(instance == null){
            instance = new SingletonExample();
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
