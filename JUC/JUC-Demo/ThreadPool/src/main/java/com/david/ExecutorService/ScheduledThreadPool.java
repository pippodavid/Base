package com.david.ExecutorService;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
线程池提供了一个线程队列,队列中保存着所有等待状态的线程;避免了创建与销毁线程的额外开销,提高了响应速度;
* java.util.concurrent.Executor: 负责线程的使用和调度的根接口;
* ExecutorService: 子接口,线程池的主要接口;
* ScheduledExecutorService: 子接口,负责线程的调度;
*ScheduledThreadPoolExecutor: 继承了线程池的实现类,实现了负责线程调度的子接口;
* */
public class ScheduledThreadPool {
    public static void main(String[] args) throws Exception{
        // 1. 创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 2. 分配任务
        //      pool.shedule(Callalbe<T> callable, long delay, TimeUnit unit(时间单位))
        for(int i=0; i < 10; i++){
            Future<Integer> result = pool.schedule(new Callable<Integer>(){
                public Integer call() throws Exception{
                    // 产生100以内的随机数
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+ ":" + num);
                    return num;
                }
            }, 3, TimeUnit.SECONDS);
            System.out.println(result.get());
        }
        //3. 关闭线程池
        pool.shutdown();
    }
}
