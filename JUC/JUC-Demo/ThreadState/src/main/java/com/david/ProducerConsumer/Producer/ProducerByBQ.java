package com.david.ProducerConsumer.Producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/*
*利用阻塞队列来实现生产者消费者程序
* 生产者实现
 */
public class ProducerByBQ implements Runnable{
    private final BlockingQueue blockingQueue;

    public ProducerByBQ(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.put(producer());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Object producer() {
        Random random = new Random(10L);
        Integer integer = random.nextInt();
        System.out.println(Thread.currentThread().getName() + "生产了随机数" + integer.toString());
        return integer;
    }
}
