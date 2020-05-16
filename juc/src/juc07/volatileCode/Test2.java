package juc07.volatileCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证非原子性
 *
 * @author sayyes
 * @date 2020/5/13
 */
public class Test2 {
    public static void main(String[] args) {

        MyData2 myData2 = new MyData2();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 100; j++) {
                    myData2.add();//不保证原子性
                    myData2.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("num = " + myData2.num);
        System.out.println("num = " + myData2.ai);
    }
}

class MyData2 {
    volatile int num = 0;

    public void add() {
        num++;
    }

    AtomicInteger ai = new AtomicInteger();
    public void addAtomic(){
        ai.getAndIncrement();
    }
}
