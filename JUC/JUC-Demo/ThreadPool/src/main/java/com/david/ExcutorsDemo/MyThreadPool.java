package com.david.ExcutorsDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
    private final int POOL_SIZE = 5;         //线程池中线程启动数
    private final int FIXED_POOL_SIZE = 3;   //固定线程数，用于newFixedThreadPool线程模式中指定固定线程数

    private ThreadPoolType threadPoolType = ThreadPoolType.CachedType;

    public enum ThreadPoolType {
        CachedType,
        FixedType,
        SingleType
    }

    public ThreadPoolType getThreadPoolType() {
        return threadPoolType;
    }

    public void setThreadPoolType(ThreadPoolType threadPoolType) {
        this.threadPoolType = threadPoolType;
    }

    public void runThreadPool() {
        ExecutorService executorService = getExecutorService();

        for (int i = 0; i <= POOL_SIZE; i++) {
            final int index = i;
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                }
            };
            executorService.execute(runnable);
        }
    }

    private ExecutorService getExecutorService() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        switch (this.threadPoolType) {
            case CachedType:
                break;
            case FixedType:
                executorService = Executors.newFixedThreadPool(FIXED_POOL_SIZE);
                break;
            case SingleType:
                executorService = Executors.newSingleThreadExecutor();
                break;
            default:
                break;
        }
        return executorService;
    }
}
