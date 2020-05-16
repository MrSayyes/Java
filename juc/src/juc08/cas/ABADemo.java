package juc08.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AS的ABA问题解决方式AtomicStampedReference.java
 *
 * @author sayyes
 * @date 2020/5/16
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(10);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

    public static void main(String[] args) {
        System.out.println("*******************ABA问题产生*****************");
        new Thread(() -> {
            // 两次修改完成ABA操作
            atomicReference.compareAndSet(10, 20);//10改成20
            atomicReference.compareAndSet(20, 10);//20改成10
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(10, 30) + "\t" + atomicReference.get());
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);//保证上面线程执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*******************ABA问题产生*****************");
        System.out.println("*******************解决ABA问题*****************");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);//保证上面线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(10, 20, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(20, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号" + atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean r = atomicStampedReference.compareAndSet(10, 30, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改结果：" + r + "|最新版本号：" + atomicStampedReference.getStamp() + "|最新引用：" + atomicStampedReference.getReference());
        }, "t4").start();
    }
}
