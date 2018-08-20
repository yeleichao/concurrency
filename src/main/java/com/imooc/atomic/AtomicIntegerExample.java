package com.imooc.atomic;

import com.imooc.annotation.NotThreadSafe;
import com.imooc.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发测试代码
 *
 * @author yeleichao
 * @date 2018-8-19.
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerExample {
    /**请求总数*/
    public static int clientTotal = 5000;

    /**请求的并发线程执行数*/
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        long start = System.currentTimeMillis();
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
        log.info("count:{},共耗时:{}",count.get(),System.currentTimeMillis()-start);
    }

    private static void add(){

        count.incrementAndGet();

    }
}

