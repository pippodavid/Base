
import com.david.ProducerConsumerDemo.Consumer;
import com.david.ProducerConsumerDemo.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestConsumerProducer {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue();

        Producer producer = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
