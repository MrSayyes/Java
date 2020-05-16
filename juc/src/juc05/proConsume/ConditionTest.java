package juc05.proConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程顺序调用，实现A->B->C
 * 打印A线程5次，B线程10次，C线程15次，重复10轮
 *
 * 使用Condition可以实现精准线程处理
 * @author sayyes
 * @date 2020/5/4
 */
public class ConditionTest {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print15();
            }
        }, "C").start();

    }

}

class ShareData {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    //condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //1、判断，2、干活，3、通知
            while (number != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知，改标志位
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1、判断，2、干活，3、通知
            while (number != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知，改标志位
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1、判断，2、干活，3、通知
            while (number != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知，改标志位
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
