package ExecutorsTest;

import com.david.ExcutorsDemo.MyScheduledThreadPool;
import com.david.ExcutorsDemo.MyThreadPool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ThreadPoolTest {

    @Test
    public void cachedThreadPoolTest() {
        MyThreadPool myThreadPool = new MyThreadPool();   //已经默认指定了Type为cachedType，这里可以不指定了
        myThreadPool.runThreadPool();
    }

    @Test
    public void singleThreadPoolTest() {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.setThreadPoolType(MyThreadPool.ThreadPoolType.SingleType);
        myThreadPool.runThreadPool();
    }

    @Test
    public void fixedThreadPoolTest() {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.setThreadPoolType(MyThreadPool.ThreadPoolType.FixedType);
        myThreadPool.runThreadPool();
    }

    @Test
    public void scheduledThreadPoolTest_1() {
        MyScheduledThreadPool myThreadPool = new MyScheduledThreadPool();
        myThreadPool.runScheduledThreadPool_1();
    }

    @Test
    public void scheduledThreadPoolTest_2() {
        MyScheduledThreadPool myThreadPool = new MyScheduledThreadPool();
        myThreadPool.runScheduledThreadPool_2();
    }
}
