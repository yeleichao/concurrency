package com.imooc.synccontainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Vector
 *
 * @author yeleichao
 * @date 2018-8-21.
 */
@Slf4j
public class VectorExample1 {
    /**请求总数*/
    public static int clientTotal = 5000;

    /**请求的并发线程执行数*/
    public static int threadTotal = 200;

    //public static Vector<Integer> vector = new Vector<>();
    //public static Map<Integer, Integer> map = Maps.newHashMap();
    public static Map<Integer, Integer> map = Maps.newConcurrentMap();
    //public static List<Integer> list = new ArrayList<>();
    public static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal;i++){
            final int count = i;

            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",list.size());
    }

    private static void add(int i){
        //vector.add(i);
        //map.put(i,i);
        list.add(i);
    }
}
