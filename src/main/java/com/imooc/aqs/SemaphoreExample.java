package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号量，表示同一时刻有多少线程可以执行
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class SemaphoreExample {

    private final static int THREAD_LOCAL = 20;

    public static void main(String[] args) throws Exception {
        final Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<THREAD_LOCAL;i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                   if( semaphore.tryAcquire(5,TimeUnit.SECONDS)){//5秒之后就结束执行，其他未执行的线程就不执行了
                        //semaphore.acquire();//获取许可
                        add(count);
                        semaphore.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
        executorService.shutdown();

    }

    private static void add(int i) throws InterruptedException {
        log.info("{}",i);
        TimeUnit.SECONDS.sleep(3);
    }
}
