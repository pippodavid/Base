package com.david;

import java.util.concurrent.atomic.AtomicStampedReference;

public class test1 {
    public static void main(String[] args) {
        final  MyInc myInc = new MyInc();
        for (int i = 1; i <= 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 1; j <= 10000; j++) {
                        myInc.addByInt();
                        myInc.addByAtomic();
                    }
                }
            }).start();
        }
        //需要等new的50个线程执行结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("Result of addByInt is：" + myInc.num);
        System.out.println("Result of addByAtomic is：" + myInc.atomicInteger);
    }
}


