package com.david.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
* 当然，ReentrantLock还提供了另外一个更好的方法解决死锁问题，那就是使用tryLock()方法，
* 该方法会尝试获得锁，如果成功，返回true,失败则返回false。
* */
public class DeadLockByRLTryLock implements Runnable{

        private boolean      flag;
        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();
        //统计发生死锁的次数
        private static int count;
        public DeadLockByRLTryLock(boolean flag) {
            this.flag = flag;
        }

        public void run() {
            if (flag) {
                while (true) {
                    //这里使用表示tryLock() 立即返回，使用tryLock(long timeout, TimeUnit unit)等待一段时间后返回
                    if (lock1.tryLock()) {
                        System.out.println(flag + "线程获得了lock1");
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                            try {
                                if (lock2.tryLock()) {
                                    System.out.println(flag + "获得了lock2");
                                }
                            } finally {
                                //同时获得Lock1和lock2，没有发生死锁，任务完成，退出循环
                                if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
                                    System.out.println(flag + "线程执行完毕" + "---------------------");
                                    lock1.unlock();
                                    lock2.unlock();
                                    break;
                                } else {
                                    //说明发生了死锁，只需要释放lock1
                                    count++;
                                    System.out.println("发生了" + count + "次死锁");
                                    lock1.unlock();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                while (true) {
                    if (lock2.tryLock()) {
                        System.out.println(flag + "线程获得了lock2");
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                            try {
                                if (lock1.tryLock()) {
                                    System.out.println(flag + "线程获得了lock1");
                                }
                            } finally {
                                if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
                                    System.out.println(flag + "线程执行完毕" + "---------------------");
                                    lock1.unlock();
                                    lock2.unlock();
                                    break;
                                } else {
                                    count++;
                                    System.out.println("发生了" + count + "次死锁");
                                    lock2.unlock();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
