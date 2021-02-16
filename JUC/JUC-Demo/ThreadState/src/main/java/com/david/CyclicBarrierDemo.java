package com.david;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int THEAD_COUNT = 3;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(THEAD_COUNT);
    private ConcurrentHashMap<String, Integer> countMap = new ConcurrentHashMap<String, Integer>();

    public void runCyclicBarrier() {
        Runnable runnable = new Runnable() {
            public void run() {
                int result = 0;

                for(Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    result += entry.getValue();
                }
                countMap.put(Thread.currentThread().getName() + "result", result);
            }
        };

        if (cyclicBarrier.isBroken()) {
            cyclicBarrier.reset();
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
