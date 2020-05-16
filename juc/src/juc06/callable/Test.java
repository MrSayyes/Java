package juc06.callable;

import java.util.concurrent.Callable;

/**
 * callable实现线程
 * 不同：
 *      1、接口实现的方法不同
 *      2、方法一个有异常、一个没有异常
 *      3、有无返回值
 * @author sayyes
 * @date 2020/5/4
 */
public class Test {
}

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class  MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
