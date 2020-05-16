package juc08.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sayyes
 * @date 2020/5/13
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);//无参默认0
        //compareAndSet第一个参数是期望值，第二是是新值，在满足期望值后就会修改
        System.out.println(atomicInteger.compareAndSet(5, 2019));
        System.out.println("*********" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2000));
    }
}

