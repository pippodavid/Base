import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

//-Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/oomDumpFile/heapdump.hprof
public class OOMServiceDemo_1 {
    private static int i = 0;

    public ArrayList distinct() throws InterruptedException {
        final ArrayList userList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int j = 0; j < 100; j++) {
                        //这里认为公司的用户数据量是一万，正常的8M是可以存放1万个User对象的
                        for (int k = 0; k < 10000; k++) {
                            User user = new User(String.valueOf(k));

                            if (!userList.contains(user)) {
                                userList.add(user);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("eo");
        thread1.start();

        countDownLatch.await();  //等待计数器归零
        return userList;

    }

    public static void main(String[] args) throws InterruptedException{
        OOMServiceDemo_1 oomServiceDemo_1 = new OOMServiceDemo_1();
        oomServiceDemo_1.distinct();
    }
}
