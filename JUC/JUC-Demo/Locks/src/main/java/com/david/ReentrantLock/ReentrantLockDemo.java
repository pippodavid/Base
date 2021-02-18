package com.david.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* Lock能解决synchornized关键字的一些问题，主要体现以下三个方面
* 1)能够响应中断，支持中断的API
* public void lockInterruptibly() throws InterruptedException {
* }
* 2)支持超时的API
* public boolean tryLock(long time, TimeUnit unit) throws InterruptedException{
* }
* 3)支持非阻塞获取锁的API
* boolean tryLock();
* */
public class ReentrantLockDemo{
    private final Lock rt1 = new ReentrantLock();
    int value;

    //后续的线程能看到value的正确结果，因为Lock的实现利用了volatile相关的Happens-before规则
    public void addOne() {
        //获取锁
        rt1.lock();
        try {
            value += 1;
        } finally {
            //保证锁能释放
            rt1.unlock();
        }
    }

    public int get() {
        rt1.lock();
        try {
            return value;
        } finally {
            rt1.unlock();
        }
    }

    public void addOneByGet() {
        rt1.lock();
        try {
            value = 1 + get();
        } finally {
            rt1.unlock();
        }
    }
}
