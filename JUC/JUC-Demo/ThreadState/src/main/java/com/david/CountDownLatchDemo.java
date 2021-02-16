package com.david;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    private static final int THEAD_COUNT = 3;
    private static CountDownLatch countDownLatch = new CountDownLatch(THEAD_COUNT);
    private static ExecutorService executorService = Executors.newFixedThreadPool(THEAD_COUNT);

    public void runCountDown() {
        for (int i = 0; i < THEAD_COUNT; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", thead is executed");
                    countDownLatch.countDown();
                }
            });
        }

        try {
            //等待线程都到达指定“位置”
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (countDownLatch.getCount() == 0L) {
            System.out.println("All Thread is ready");
            //something else to do
            executorService.shutdown();
        }
    }
}
