package com.imooc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 推荐写法注解
 * 1、SOURCE：在原文件中有效，被编译器丢弃。
 * 2、CLASS：在class文件有效，可能会被虚拟机忽略。
 * 3、RUNTIME：在运行时有效。
 * @author yeleichao
 * @date 2018-8-18.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Recommend {
    String value() default "";
}
