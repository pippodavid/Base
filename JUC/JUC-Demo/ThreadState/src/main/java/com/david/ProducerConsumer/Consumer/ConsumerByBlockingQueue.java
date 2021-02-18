package com.david.ProducerConsumer.Consumer;

import com.david.ProducerConsumer.Resource.ResourceForBlockingQueue;

public class ConsumerByBlockingQueue extends Thread{
    private ResourceForBlockingQueue resource;

    public ConsumerByBlockingQueue(ResourceForBlockingQueue resource) {
        this.resource = resource;
        //setName("消费者");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}
