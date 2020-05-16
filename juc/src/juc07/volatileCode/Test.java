package juc07.volatileCode;

import java.util.concurrent.TimeUnit;

/**
 * volatile验证可见性
 *
 * @author sayyes
 * @date 2020/5/12
 */
public class Test {
    public static void main(String[] args) {
        MyData myData = new MyData();
        //第一个线程
        new Thread(() -> {
            System.out.println("come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.to60();//改变
            System.out.println("update num=" + myData.num);
        }, "A").start();

        //第二个线程
        while (myData.num == 0) {
            //等待
        }
        System.out.println("main over");
    }
}

class MyData {
    //加volatile就不会造成main线程一直等待
//    int num = 0;
    volatile int num = 0;

    public void to60() {
        this.num = 60;
    }
}