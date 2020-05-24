package juc04.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sayyes
 * @date 2020/5/18
 */
public class LockConditionDemo {
    public static void main(String[] args) {
        MyData3 myData3 = new MyData3();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                myData3.printFive();
            }
        }, String.valueOf("AAA")).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                myData3.printTen();
            }
        }, String.valueOf("BBB")).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                myData3.printFifteen();
            }
        }, String.valueOf("CCC")).start();

    }
}

class MyData3 {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printFive() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            number = 2;
            for (int i = 1; i <= 5; i++) {
                System.out.println("*********AAA" + i);
            }
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printTen() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            number = 3;
            for (int i = 1; i <= 10; i++) {
                System.out.println("*********BBB" + i);
            }
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printFifteen() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            number = 1;
            for (int i = 1; i <= 15; i++) {
                System.out.println("*********CCC" + i);
            }
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
