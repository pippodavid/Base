package com.david;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    //线程个数大家根据自己的机器情况设置，不启爆了就行
    //private static final int THEAD_COUNT = 20;
    //private static ExecutorService executorService = Executors.newFixedThreadPool(THEAD_COUNT);

    //volatile不能保证原子性
    volatile static int num = 0;
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void addByInt() {
       num++;
    }

    public static void addByAtomicInteger() {
       atomicInteger.incrementAndGet();
    }

    public int getNum()  {
        return num;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 1; j <= 1000; j++) {
                        addByInt();
                    }
                }
            }).start();
        }
        //需要等new的20个线程执行结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("非原子的++结果：" + num);
    }

    /*@Test
    public void testIntInc() throws InterruptedException {
        for (int i = 1; i <= 20; i++) {
                new Thread(new Runnable() {
                    public void run() {
                        for (int j = 1; j <= 1000; j++) {
                            addByInt();
                        }
                    }
                }).start();
            }
            //需要等new的20个线程执行结束
            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
            System.out.println("非原子的++结果：" + num);
    }

    @Test
    public void testIntegerInc() throws InterruptedException{
        for (int i = 1; i <= 20; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 1; j <= 1000; j++) {
                        addByAtomicInteger();
                    }
                }
            }).start();
        }
        //需要等new的20个线程执行结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("原子的++结果：" + atomicInteger);
    }*/

    /*@Test
        public void test() {
            MyData myData = new MyData();
            for (int i = 1; i <= 20; i++) {
                new Thread(() -> {
                    for (int j = 1; j <= 1000; j++) {
                        myData.addPlusPlus();
                        myData.addMyAtomic();
                    }
                }).start();
            }
            //需要等new的20个线程执行结束
            while (Thread.activeCount() > 2) {
                Thread.yield();
            }
            System.out.println("非原子的++结果：" + myData.num);
            System.out.println("原子的++结果：" + myData.atomicInteger);
        }

    class MyData {

        volatile int num = 0;
        AtomicInteger atomicInteger = new AtomicInteger();

        public void addPlusPlus() {
            num++;
        }

        public void addMyAtomic() {
            atomicInteger.getAndIncrement();
        }
    }*/
}
