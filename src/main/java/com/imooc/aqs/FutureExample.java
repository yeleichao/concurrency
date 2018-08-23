package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future Callable
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class FutureExample {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do someing in main");
        String result = future.get();
        log.info("{}",result);
    }

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("into callable");
            TimeUnit.SECONDS.sleep(5);
            return "Done";
        }
    }
}
