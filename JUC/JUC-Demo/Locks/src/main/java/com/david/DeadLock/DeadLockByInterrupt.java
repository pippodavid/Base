package com.david.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
* 但是如果调用带超时的tryLock方法reentrantLock.tryLock(long timeout, TimeUnit unit)，那么如果线程在等待时被中断，将抛出一个InterruptedException异常，这是一个非常有用的特性，因为它允许程序打破死锁。
* 你也可以调用reentrantLock.lockInterruptibly()方法，它就相当于一个超时设为无限的tryLock方法
*主线程对Thread1进行了中断，thread1抛出异常，异常被捕获，
*在finally中释放thread1获得的锁，线程2获得需要的锁，该线程得以继续执行，死锁就被解决了
* */
public class DeadLockByInterrupt implements Runnable{
        private boolean      flag;
        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();

        public DeadLockByInterrupt(boolean flag) {
            this.flag = flag;
        }

        public void run() {
            try {
                if (flag) {
                    //可中断地加锁
                    lock1.lockInterruptibly();
                    System.out.println(flag + "线程获取了lock1");
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                    System.out.println(flag + "线程获取了lock2");
                } else {
                    lock2.lockInterruptibly();
                    System.out.println(flag + "线程获取lock2");
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lockInterruptibly();
                    System.out.println(flag + "线程获取了lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                    System.out.println(flag + "线程释放lock1锁");
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                    System.out.println(flag + "线程释放lock2锁");
                }
                System.out.println(flag + "线程已退出");
            }
        }
    }
