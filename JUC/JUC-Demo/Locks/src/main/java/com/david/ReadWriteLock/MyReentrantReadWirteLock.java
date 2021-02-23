package com.david.ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写锁支持Condition，读锁不支持Condition。
 * 支持锁降级，即写锁可以获取读锁，然后释放写锁，这样写锁就变成了读锁
 * 不支持锁升级，即读锁不能直接获取写锁。需将读锁释放后再获取写锁，支持锁中断
 * 读写锁的最大数量只能是65535（包括重入数）
 */
public class MyReentrantReadWirteLock {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private int resource = 0;

    public int read() {
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取读锁,尝试读取");
            return resource;
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        }
    }

    public void write(int newValue) {
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取写锁,尝试写入");
            this.resource = newValue;
            System.out.println(Thread.currentThread().getName() + "写入值 " + newValue);
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
        }
    }
}
