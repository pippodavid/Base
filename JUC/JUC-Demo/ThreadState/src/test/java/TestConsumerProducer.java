import com.david.ProducerConsumer.Consumer.ConsumerByBQ;
import com.david.ProducerConsumer.Consumer.ConsumerByBlockingQueue;
import com.david.ProducerConsumer.Consumer.ConsumerByLockCondition;
import com.david.ProducerConsumer.Consumer.ConsumerBySyn;
import com.david.ProducerConsumer.Producer.ProducerByBQ;
import com.david.ProducerConsumer.Producer.ProducerByBlockingQueue;
import com.david.ProducerConsumer.Producer.ProducerByLockCondition;
import com.david.ProducerConsumer.Producer.ProducerBySyn;
import com.david.ProducerConsumer.Resource.ResourceForBlockingQueue;
import com.david.ProducerConsumer.Resource.ResourceForLockCondition;
import com.david.ProducerConsumer.Resource.ResourceForSyn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 生产者生产数据到缓冲区中，消费者从缓冲区中取数据。
* 如果缓冲区已经满了，则生产者线程阻塞；
* 如果缓冲区为空，那么消费者线程阻塞。
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestConsumerProducer {

    /*
    * 通过阻塞队列实现生产者-消费者线程
    * 生产过程在生产者中实现，消费过程在消费者中实现
    * */
    @Test
    public void testProducerConsumerByBQ_1() {
        BlockingQueue queue = new LinkedBlockingQueue();

        ProducerByBQ producerByBQ = new ProducerByBQ(queue);
        ConsumerByBQ consumerByBQ1 = new ConsumerByBQ(queue);
        ConsumerByBQ consumerByBQ2 = new ConsumerByBQ(queue);

        new Thread(producerByBQ).start();
        new Thread(consumerByBQ1).start();
        new Thread(consumerByBQ2).start();
    }

    /*
    * 和前一个类似，同样都是使用阻塞队列来实现生产者消费者线程
    * 不同的是这里使用了一个Resource类来描述消费过程
    * */
    @Test
    public void testProducerConsumerByBQ_2() {
        ResourceForBlockingQueue resource = new ResourceForBlockingQueue();
        //生产者线程
        ProducerByBlockingQueue p = new ProducerByBlockingQueue(resource);
        //多个消费者
        ConsumerByBlockingQueue c1 = new ConsumerByBlockingQueue(resource);
        ConsumerByBlockingQueue c2 = new ConsumerByBlockingQueue(resource);
        ConsumerByBlockingQueue c3 = new ConsumerByBlockingQueue(resource);

        p.start();
        c1.start();
        c2.start();
        c3.start();
    }

    /*
    * 测试LockCondition类
    * */
    @Test
    public void testProducerConsumerByLC() {
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        ResourceForLockCondition resource = new ResourceForLockCondition(lock,producerCondition,consumerCondition);

        //生产者线程
        ProducerByLockCondition producer1 = new ProducerByLockCondition(resource);

        //消费者线程
        ConsumerByLockCondition consumer1 = new ConsumerByLockCondition(resource);
        ConsumerByLockCondition consumer2 = new ConsumerByLockCondition(resource);
        ConsumerByLockCondition consumer3 = new ConsumerByLockCondition(resource);

        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }

    @Test
    public void testProcucerConsumerBySyn() {
        ResourceForSyn resource = new ResourceForSyn();
        //生产者线程
        ProducerBySyn p1 = new ProducerBySyn(resource);
        ProducerBySyn p2 = new ProducerBySyn(resource);
        ProducerBySyn p3 = new ProducerBySyn(resource);
        //消费者线程
        ConsumerBySyn c1 = new ConsumerBySyn(resource);
        //ConsumerThread c2 = new ConsumerThread(resource);
        //ConsumerThread c3 = new ConsumerThread(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        //c2.start();
        //c3.start();
    }
}
