import org.openjdk.jol.info.ClassLayout;

public class LockTest {
    public static void main(String[] args) throws Exception{
        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("Pojo.Person 对象：");
        System.out.println(layout.toPrintable());

        synchronized (a) {
            System.out.println("Pojo.Person 第一次被锁定");
            System.out.println(layout.toPrintable());
        }

        Thread.sleep(1000L);
        System.out.println("锁释放");
        System.out.println(layout.toPrintable());
    }

    public static class A {
    }
}
