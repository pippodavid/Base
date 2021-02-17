package com.david.ExcutorsDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPool {
    private final int CORE_POOL_SIZE = 3;    //核心线程数，用于newScheduledThreadPool线程模式中指定核心线程数

    public void runScheduledThreadPool_1() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

        //指定延迟时间周期性的执行
        executorService.scheduleWithFixedDelay((new Runnable() {
            public void run() {
                System.out.println("scheduled模式执行周期延迟执行，1秒钟后每3秒执行一次");
            }
        }), 1, 3, TimeUnit.SECONDS);

        //以下方法类似的效果，参数依次为运行任务，初始延迟，两次执行间延时，时间单位
        /*executorService.scheduleAtFixedRate((new Runnable() {
            public void run() {
                System.out.println("scheduled模式执行周期延迟执行，1秒钟后每3秒执行一次");
            }
        }), 1, 3, TimeUnit.SECONDS);*/
    }

    public void runScheduledThreadPool_2() {

        ScheduledExecutorService  executorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("scheduled模式执行，延迟3秒");
            }
        };
        executorService.schedule(runnable, 3, TimeUnit.SECONDS);
    }
}
