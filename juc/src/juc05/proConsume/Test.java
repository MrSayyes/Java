package juc05.proConsume;

/**
 * 生产消费
 *
 * @author sayyes
 * @date 2020/5/3
 */
public class Test {
    public static void main(String[] args) {

        AirCondition airCondition = new AirCondition();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class AirCondition {
    private int number = 0;

    public synchronized void increment() throws Exception {
        //1、判断
        while (number != 0) {
            this.wait();
        }
        //2、干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3、通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        //1、判断
        while (number == 0) {
            this.wait();
        }
        //2、干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3、通知
        this.notifyAll();
    }
}
