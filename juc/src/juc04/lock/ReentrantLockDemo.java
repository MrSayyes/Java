package juc04.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（递归锁），证明reentrantLock/synchronized是可重入锁
 * 执行结果：sendEmail外层函数，sendSMS内层函数【内层对外层透明】，外层获得锁，内层自动获取锁
 * 证明synchronized是可重入锁：
 *   t1	******sendEmail
 *   t1	******sendSMS
 *   t2	******sendEmail
 *   t2	******sendSMS
 * 证明reentrantLock可重入锁：
 *   t3	******get
 *   t3	******set
 *   t4	******get
 *   t4	******set
 * @author sayyes
 * @date 2020/5/16
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        // 可重入锁（递归锁）
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            try {
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);//保证前面的线程执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        //证明reentrantLock是可重入锁
        Thread t3 = new Thread(phone2, "t3");
        Thread t4 = new Thread(phone2, "t4");
        t3.start();
        t4.start();
    }
}

/**
 * 资源类
 */
class Phone2 implements  Runnable{
    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t******sendEmail");
        sendSMS();
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t******sendSMS");
    }

    @Override
    public void run() {
        get();
    }

    ReentrantLock reentrantLock = new ReentrantLock();
    private void get() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t******get");
            set();
        }finally {
            reentrantLock.unlock();
        }
    }

    private void set() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t******set");
        }finally {
            reentrantLock.unlock();
        }
    }

}