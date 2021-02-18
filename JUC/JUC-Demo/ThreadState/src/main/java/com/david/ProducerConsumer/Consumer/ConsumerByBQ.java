package com.david.ProducerConsumer.Consumer;

import java.util.concurrent.BlockingQueue;

/*
 *利用阻塞队列来实现生产者消费者程序
 * 消费者实现
 */
public class ConsumerByBQ implements Runnable{
    private final BlockingQueue blockingQueue;

    public ConsumerByBQ(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                consume(blockingQueue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consume(Object o) {
        System.out.println(Thread.currentThread().getName() + "消费者消费了" + o.toString());
    }
}
