import Semaphore.ABCPrintBySemaphore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestABCPrint {

    @Test
    public void testABCPrint() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ABCPrintBySynchornized pa = new ABCPrintBySynchornized("A", c, a);
        ABCPrintBySynchornized pb = new ABCPrintBySynchornized("B", a, b);
        ABCPrintBySynchornized pc = new ABCPrintBySynchornized("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);// 保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }


    @Test
    public void testABCPrintBySemaphore() {
        new ABCPrintBySemaphore.ThreadA().start();
        new ABCPrintBySemaphore.ThreadB().start();
        new ABCPrintBySemaphore.ThreadC().start();
    }

}
