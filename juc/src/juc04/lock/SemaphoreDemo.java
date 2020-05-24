package juc04.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 *
 * @author sayyes
 * @date 2020/5/17
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //信号灯：多个线程抢多个资源，在配置1的时候相当于lock和sync

        Semaphore semaphore = new Semaphore(3);//3个停车位
        for (int i = 1; i <= 6; i++) {//6部汽车
            final int temp = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();//抢到
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    //暂停
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停3秒离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放资源
                }
            }, String.valueOf(i)).start();
        }
    }
}


