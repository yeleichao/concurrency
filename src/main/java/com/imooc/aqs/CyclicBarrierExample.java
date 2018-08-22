package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CyclicBarrier
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int count = i;
            TimeUnit.SECONDS.sleep(1);
            executorService.execute(() -> {
                try {
                    race(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                   log.error("{}",e);
                }
            });
        }

        executorService.shutdown();

    }

    private static void race(int count) throws InterruptedException, BrokenBarrierException {
        TimeUnit.SECONDS.sleep(1);
        log.info("Thread is ready->{}",count);
        cyclicBarrier.await();
        log.info("Thread is start->{}",count);
    }
}
