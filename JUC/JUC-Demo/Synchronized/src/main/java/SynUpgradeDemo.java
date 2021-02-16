import org.junit.jupiter.api.BeforeAll;
import org.openjdk.jol.info.ClassLayout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SynUpgradeDemo {

    final A a = new A();
    @BeforeAll
    public void initObj() {
        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("Person 对象：");
        System.out.println(layout.toPrintable());
    }

    @Test
    public void testBiasLock() throws Exception {
        ClassLayout layout = ClassLayout.parseInstance(a);

        synchronized (a) {
            System.out.println("Person 第一次被锁定");
            System.out.println(layout.toPrintable());
        }

        Thread.sleep(1000L);
        System.out.println("锁释放");
        System.out.println(layout.toPrintable());
    }

    @Test
    public void testLightLock() throws Exception {
        ClassLayout layout = ClassLayout.parseInstance(a);

        //这里升级到轻量级锁
        synchronized (a) {
            System.out.println("Person 第一次被锁定");
            System.out.println(layout.toPrintable());
        }

        new Thread(() -> {
            synchronized (a) {
                System.out.println("Person 第二次被锁定");
                System.out.println(layout.toPrintable());
            }
        }).start();

        Thread.sleep(1000L);
        System.out.println("锁释放");
        System.out.println(layout.toPrintable());
    }

    @Test
    public void testHeavyLock() throws Exception {
        ClassLayout layout = ClassLayout.parseInstance(a);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("Person 第一次被锁定");
                    System.out.println(layout.toPrintable());
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("Person 第二次被锁定");
                    System.out.println(layout.toPrintable());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        thread1.sleep(3000L);
        thread2.sleep(3000L);
        System.out.println("锁释放");
        System.out.println(layout.toPrintable());
    }

    public static class A {
    }
}
