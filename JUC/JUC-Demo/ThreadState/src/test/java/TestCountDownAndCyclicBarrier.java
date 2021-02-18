import com.david.CountDownLatch.CountDownLatchDemo;
import com.david.CyclicBarrier.CyclicBarrierDemo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCountDownAndCyclicBarrier {

    private ApplicationContext context;
    private CountDownLatchDemo countDownLatchDemo;
    private CyclicBarrierDemo cyclicBarrierDemo;

    @BeforeAll
    public void getBean() {
        context = new ClassPathXmlApplicationContext("beanConfig.xml");
        countDownLatchDemo = (CountDownLatchDemo) context.getBean("countDownLatchDemo");
        cyclicBarrierDemo = (CyclicBarrierDemo) context.getBean("cyclicBarrierDemo");
    }

    @Test
    public void TestCountDownLatch() throws InterruptedException{
        countDownLatchDemo.runCountDown();
    }

    @Test
    public void TestCyclicBarrier() throws InterruptedException {
        cyclicBarrierDemo.runCalcutlate();
    }

}
