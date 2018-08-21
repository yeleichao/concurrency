package com.imooc.threadlocal;

/**
 * RequestHolder
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requsetHolder = new ThreadLocal<>();

    public static void add(Long id){
        requsetHolder.set(id);
    }

    public static Long getId(){
       return requsetHolder.get();
    }

    public static void remove(){
        requsetHolder.remove();
    }
}
