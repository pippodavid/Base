package com.david;

import java.util.concurrent.atomic.AtomicInteger;

public class MyInc {
        volatile int num = 0;  //volatile不能保证原子性
        AtomicInteger atomicInteger = new AtomicInteger();

        public void addByInt() {
            num++;
        }

        public void addByAtomic() {
            atomicInteger.getAndIncrement();
        }
}
