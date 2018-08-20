package com.imooc.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 希望某段代码只执行一次，可以参考下面的实现
 *
 * @author yeleichao
 * @date 2018-8-19.
 */
@Slf4j
public class AtomicBooleanExample {

    /**请求总数*/
    public static int clientTotal = 5000;

    /**请求的并发线程执行数*/
    public static int threadTotal = 200;

    public static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        long start = System.currentTimeMillis();
        for (int i=0;i<clientTotal;i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{},共耗时:{}",isHappened.get(),System.currentTimeMillis()-start);
    }

    private static void test(){
        if(isHappened.compareAndSet(false, true)){
            log.info("test");
        }


    }
}
