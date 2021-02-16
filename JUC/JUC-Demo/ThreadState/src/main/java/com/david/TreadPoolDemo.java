package com.david;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

public class TreadPoolDemo {
    public void test() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque();
        MyThreadPool pool = new MyThreadPool();
    }
    class MyThreadPool {
        //利用阻塞队列实现生产者-消费者模式
        public void executorTest() {
        }
    }
}
