/*
* 这里用synchornzied来实现ABC的交替打印
* */
public class ABCPrintBySynchornized implements Runnable{

    //总共两个个对象，这里定义一个前置对象和本身，定义本身和后置对象也是可以的，反正是有两个对象，无所谓
    private String name;
    private Object prev;
    private Object self;

    public ABCPrintBySynchornized(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    /*
    * run()方法，通过synchornized锁两个对象来实现
    * 在实际编程中，我们应该尽量在线程调用notify/notifyAll()后，立即退出临界区。
    * 只有退出临界区了才会释放锁，即不要在notify/notifyAll()后面再写一些耗时的代码。
    * */
    @Override
    public void run() {
        int count = 20;     //打印的总次数
        while (count > 0) {
            synchronized (prev) {     //先获取prev锁
                synchronized (self) {   //再获取self锁
                    System.out.println(this.name);
                    count--;

                    self.notifyAll();   //唤醒其他线程竞争self锁,这里self锁并没没有立即释放
                }
                try {
                    if (count == 0) {  //表示最后一次打印操作，通过notifyAll操作释放锁
                        prev.notifyAll();
                    } else {
                        prev.wait();   //立即释放prev，当前线程休眠等待唤醒
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
