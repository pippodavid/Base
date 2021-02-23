package com.david.ReentrantLock;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptRLDemo {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    public void runInterruptLock() {

        boolean captured = false;
        try {
            //在2秒内尝试获取锁
            captured = reentrantLock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("tryLock(10, TimeUnit.SECONDS)" + captured);
        } finally {
            if (captured) {
                reentrantLock.unlock();
            }
        }
    }

    public void runInterruptLockByOther() {
        try {
            reentrantLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获取锁");
            try {
                Thread.sleep(5 * 1000);
                //reentrantLock.plusMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + "被中断");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }
    }
}
