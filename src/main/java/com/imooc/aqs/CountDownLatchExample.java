package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class CountDownLatchExample {

    private final static int clientThread = 10;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDown = new CountDownLatch(clientThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0; i<clientThread;i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    add(count);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDown.countDown();
                }
            });
        }

        /** countDown.await(10,TimeUnit.SECONDS);表示如果线程运行超过10秒，则无需等待*/
        countDown.await(2,TimeUnit.SECONDS);
        log.info("finshed");
    }

    private static void add(int i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        log.info("{}->{}",Thread.currentThread().getName(),i);

    }
}
