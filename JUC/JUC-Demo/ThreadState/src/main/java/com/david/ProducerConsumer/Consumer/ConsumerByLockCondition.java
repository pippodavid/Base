package com.david.ProducerConsumer.Consumer;

import com.david.ProducerConsumer.Resource.ResourceForLockCondition;

public class ConsumerByLockCondition extends Thread{
    private ResourceForLockCondition resource;
    public ConsumerByLockCondition(ResourceForLockCondition resource){
        this.resource = resource;
        //setName("消费者");
    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}
