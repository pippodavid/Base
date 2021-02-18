package com.david.ProducerConsumer.Producer;

import com.david.ProducerConsumer.Resource.ResourceForSyn;

public class ProducerBySyn extends Thread{
    private ResourceForSyn resource;
    public ProducerBySyn(ResourceForSyn resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        //不断地生产资源
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}
