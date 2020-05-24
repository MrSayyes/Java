package juc09.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue:是一个基于数组机构的有界阻塞队列，此队列按FIFO原则对元素进行排序
 * LinkedBlockingDeque:一个基于链表机构的阻塞队列，此队列按FIFO排序元素，吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue:一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于ArrayBlockingQueue
 *
 * @author sayyes
 * @date 2020/5/17
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));//true
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));//true
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));//true
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));//false

        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));//a
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));//b
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));//c
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));//null
    }


    //ArrayBlockingQueue 的 offer、poll、peek
    public static void ArrayBlockingQueueMethod3() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");//队列满阻塞

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());//消费完阻塞
    }

    //ArrayBlockingQueue 的 offer、poll、peek
    public static void ArrayBlockingQueueMethod2() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//不抛异常

        System.out.println(blockingQueue.peek());//检查队列头部元素a

        System.out.println(blockingQueue.poll());//a
        System.out.println(blockingQueue.poll());//b
        System.out.println(blockingQueue.poll());//c
        System.out.println(blockingQueue.poll());//null
    }

    //ArrayBlockingQueue 的 add、remove、element
    public static void ArrayBlockingQueueMethod() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //队列插入异常：java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //队列清除异常：java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }
}
