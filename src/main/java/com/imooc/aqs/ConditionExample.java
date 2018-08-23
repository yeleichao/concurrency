package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 可以先让一个线程停止并释放锁，然后执行其他线程时通过
 * condition.signalAll 来通知那个线程可以继续执行
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class ConditionExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
           lock.lock();
            log.info("locked"); //1
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get sinal");//4
            lock.unlock();
        }).start();

        new Thread(() -> {
            lock.lock();
            log.info("get lock"); //2
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send sinal");//3
            lock.unlock();
        }).start();
    }
}
