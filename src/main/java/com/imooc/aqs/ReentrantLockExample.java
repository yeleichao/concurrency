package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class ReentrantLockExample {
    /**请求总数*/
    public static int clientTotal = 5000;

    /**请求的并发线程执行数*/
    public static int threadTotal = 200;

    public static int count = 0;

    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal;i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        reentrantLock.lock();
        try {
            count++;
        }finally {
            reentrantLock.unlock();
        }

    }
}
