package com.david.ProducerConsumer.Consumer;

import com.david.ProducerConsumer.Resource.ResourceForSyn;

public class ConsumerBySyn extends Thread {

    private ResourceForSyn resource = new ResourceForSyn();

    public ConsumerBySyn(ResourceForSyn resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}
