package com.david.ProducerConsumer.Producer;

import com.david.ProducerConsumer.Resource.ResourceForLockCondition;

public class ProducerByLockCondition extends Thread {
    private ResourceForLockCondition resource;
    public ProducerByLockCondition(ResourceForLockCondition resource){
        this.resource = resource;
        //setName("生产者");
    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}
