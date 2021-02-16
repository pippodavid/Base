import com.david.AtomicIntegerDemo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAtomicInteger {
    private ApplicationContext applicationContext;
    private AtomicIntegerDemo atomicIntegerDemo;

    //我的是32位机器，4G内存，启10000个线程可以，大家根据自己的机器情况设置，不启爆了就行
    private static final int THEAD_COUNT = 10000;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THEAD_COUNT);

    @BeforeAll
    public void getBeanOfAtomicInteger() {
        applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        atomicIntegerDemo = (AtomicIntegerDemo)applicationContext.getBean("atomicIntegerDemo");
    }

    @Test
    public void testIntegerInc() {
        Runnable runnable = new Runnable() {
            public void run() {
                atomicIntegerDemo.addByAtomicInteger();
            }
        };
        for (int i = 0; i < THEAD_COUNT; i++) {
            executorService.submit(runnable);
        }
        System.out.println(atomicIntegerDemo.getNum());
    }

    @Test
    public void testIntInc() {
        Runnable runnable = new Runnable() {
            public void run() {
                atomicIntegerDemo.addByInt();
            }
        };
        for (int i = 0; i < THEAD_COUNT; i++) {
            executorService.submit(runnable);
        }
        System.out.println(atomicIntegerDemo.getNum());
    }
}
