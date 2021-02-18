package com.david.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo_2 {
    private int balance;
    private final Lock lock = new ReentrantLock();

    //这里会出现活锁，两个线程互相谦让
    public void transfer(ReentrantLockDemo_2 rt, int amt) {
        while (true) {
            if (this.lock.tryLock()) {
                try {
                    if (rt.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            rt.balance += amt;
                        } finally {
                            rt.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }
}
