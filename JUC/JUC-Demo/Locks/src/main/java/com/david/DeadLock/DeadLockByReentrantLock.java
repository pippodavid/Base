package com.david.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockByReentrantLock implements Runnable{
    private boolean flag;
    private static ReentrantLock lock1=new ReentrantLock();
    private static ReentrantLock lock2=new ReentrantLock();

    public DeadLockByReentrantLock(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        try {
            if (flag) {
                lock1.lock();
                System.out.println(flag + "线程获取了Lock1");
                TimeUnit.SECONDS.sleep(1);
                lock2.lock();
                System.out.println(flag + "线程获取了Lock2");
            } else {
                lock2.lock();
                System.out.println(flag + "线程获取了Lock2");
                TimeUnit.SECONDS.sleep(1);
                lock1.lock();
                System.out.println(flag + "线程获取了Lock1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }
}
