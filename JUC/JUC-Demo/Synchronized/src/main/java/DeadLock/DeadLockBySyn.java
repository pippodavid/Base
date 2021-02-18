package DeadLock;

import java.util.concurrent.TimeUnit;

public class DeadLockBySyn implements Runnable {
    private boolean flag;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public DeadLockBySyn(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        if (flag) {
            synchronized (lock1) {
                System.out.println(flag + "线程拿到了lock1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(flag + "线程拿到了lock2");
                }
            }
        } else {
            synchronized (lock2) {
                System.out.println(flag + "线程拿到了lock2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(flag + "线程拿到了lock1");
                }
            }
        }
    }
}
