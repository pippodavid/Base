package com.david;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 这里注意线程等待通知要使用await(),singalAll(),singnal()
* 这些方法都是Lock&Condition使用的
* 不要使用wait(),notify(),notifyAll() (这些方法在synchornized里面使用)
* */
public class ReentrantLockDemo_3 {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    //入列
    void enq() throws InterruptedException {
        lock.lock();
        try {
            while (队列已满) {
                notFull.await();
            }
            //省略入队操作
            //入队后，通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    //出队
    void deq() throws InterruptedException{
        lock.lock();
        try {
            while (队列已空) {
                notEmpty.await();
            }
            // 省略入队操作
            // 入队后，通知可出队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
