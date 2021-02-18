package com.david.ProducerConsumer.Producer;

import com.david.ProducerConsumer.Resource.ResourceForBlockingQueue;

public class ProducerByBlockingQueue extends Thread{
    private ResourceForBlockingQueue resource;
    public ProducerByBlockingQueue(ResourceForBlockingQueue resource) {
        this.resource = resource;
        //setName("生产者");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}
