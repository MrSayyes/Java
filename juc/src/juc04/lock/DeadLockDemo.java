package juc04.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁案例
 *
 * @author sayyes
 * @date 2020/5/21
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), String.valueOf("AAA")).start();
        new Thread(new HoldLockThread(lockB, lockA), String.valueOf("BBB")).start();
    }
}

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockA + "\t尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockB + "\t尝试获取" + lockA);
            }
        }
    }
}
