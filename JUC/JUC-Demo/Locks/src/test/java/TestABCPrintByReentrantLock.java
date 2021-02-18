import com.david.ReentrantLock.ABCPrintByRLCondition;
import com.david.ReentrantLock.ABCPrintByReentrantLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestABCPrintByReentrantLock {

    @Test
    public void testABCPrint() {
        new ABCPrintByReentrantLock.ThreadA().start();
        new ABCPrintByReentrantLock.ThreadB().start();
        new ABCPrintByReentrantLock.ThreadC().start();
    }

    @Test
    public void testABCPrintByRLContdition() {
        new ABCPrintByRLCondition.ThreadA().start();
        new ABCPrintByRLCondition.ThreadB().start();
        new ABCPrintByRLCondition.ThreadC().start();
    }
}
