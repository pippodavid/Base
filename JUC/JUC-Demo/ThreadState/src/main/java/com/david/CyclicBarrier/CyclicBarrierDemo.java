package com.david.CyclicBarrier;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {
    private static final int THEAD_COUNT = 3;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(THEAD_COUNT);
    private ExecutorService executorService = Executors.newFixedThreadPool(THEAD_COUNT);
    private ConcurrentHashMap<String, Integer> countMap = new ConcurrentHashMap<String, Integer>();

    public void runCyclicBarrier() {
        Runnable runnable = new Runnable(){
            public void run() {
                int result = 0;

                for(Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    result += entry.getValue();
                }
                countMap.put(Thread.currentThread().getName() + "result", result);
                //cyclicBarrier;
            }
        };
        executorService.submit(runnable);

        try {
            //等待线程都到达指定“位置”
            cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (cyclicBarrier.isBroken()) {
                cyclicBarrier.reset();
            }
        }

        System.out.println(countMap);


    }

    public void runCalcutlate() {
        Runnable runnable = new Runnable() {
            public void run() {
                countMap.put(Thread.currentThread().getName(), 10);
                try {
                    System.out.println(Thread.currentThread().getName() + "is waiting");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "is waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
