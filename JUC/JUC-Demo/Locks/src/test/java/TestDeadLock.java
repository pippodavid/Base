import com.david.DeadLock.DeadLockByInterrupt;
import com.david.DeadLock.DeadLockByRLTryLock;
import com.david.DeadLock.DeadLockByReentrantLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDeadLock {

    @Test
    public void testDeadLockByInterrupt() throws InterruptedException {
        Thread thread1 = new Thread(new DeadLockByInterrupt(true));
        Thread thread2 = new Thread(new DeadLockByInterrupt(false));
        thread1.start();
        thread2.start();

        //主线程休眠5秒
        TimeUnit.SECONDS.sleep(5);
        thread1.interrupt();
    }

    @Test
    public void testDeadLockByRLTryLock() {
        Thread thread1 = new Thread(new DeadLockByRLTryLock(true));
        Thread thread2 = new Thread(new DeadLockByRLTryLock(false));
        thread1.start();
        thread2.start();
    }

    @Test
    public void testDeadLockByReentrantLock() throws InterruptedException {
        Thread thread1 = new Thread(new DeadLockByReentrantLock(true));
        Thread thread2 = new Thread(new DeadLockByReentrantLock(false));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("主线程已结束");
    }

}
