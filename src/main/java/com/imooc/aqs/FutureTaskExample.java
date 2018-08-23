package com.imooc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask
 *
 * @author yeleichao
 * @date 2018-8-22.
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            log.info("into callable");
            TimeUnit.SECONDS.sleep(5);
            return "Done";
        });

        new Thread(futureTask).start();
        log.info("do someing in main");
        String result = futureTask.get();
        log.info("{}",result);
    }
}
