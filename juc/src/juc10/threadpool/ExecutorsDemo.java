package juc10.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author sayyes
 * @date 2020/5/19
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定数线程:一池5线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N数线程（可扩容）
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();//关闭
        }
    }
}
