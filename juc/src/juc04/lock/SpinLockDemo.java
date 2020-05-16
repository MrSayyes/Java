package juc04.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁，使用原子引用线程编写加锁、解锁方法
 *  t1线程执行，然后等待，t2执行但结果不对，一直循环，t1完成后，t2才会满足条件执行结束
 *  t1	 come in
 *  t2	 come in
 *  t1	 myunlock
 *  t2	 myunlock
 * @author sayyes
 * @date 2020/5/16
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> ar = new AtomicReference<>();
    public  void  myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        while (!ar.compareAndSet(null, thread)){

        }
    }

    public  void  myUnLock(){
        Thread thread = Thread.currentThread();
        ar.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t myunlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();//加锁
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();//解锁
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock();//加锁
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();//解锁
        }, "t2").start();
    }
}
