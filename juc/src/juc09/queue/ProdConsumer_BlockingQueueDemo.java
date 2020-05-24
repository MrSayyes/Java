package juc09.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用volatile、CAS、AtomicInteger、BlockingQueue、线程交互、原子引用
 *
 * @author sayyes
 * @date 2020/5/18
 */
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t生产开始");
            try {
                myResource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("prod")).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t消费开始");
            System.out.println();
            System.out.println();
            try {
                myResource.consumer();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("consumer")).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("5秒时间过，mian通知结束生产消费");
        myResource.stop();//改变标志位
    }
}

class MyResource {
    private volatile boolean FLAG = true;//volatile实现可见性
    private AtomicInteger atomicInteger = new AtomicInteger();//AtomicInteger

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void prod() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println("插入" + data + "成功");
            } else {
                System.out.println("插入" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);//1秒一次
        }
        System.out.println("被叫停，停止生产Flag=" + FLAG);
    }

    public void consumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println("消费了" + result);
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}
