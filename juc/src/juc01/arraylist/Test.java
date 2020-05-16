package juc01.arraylist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sayyes
 * @date 2020/4/28
 *
 * ArrayList：
 *    1）线程不安全,抛错java.util.ConcurrentModificationException
 *    2）ArrayList初始值大小是10，每次按原值的一半扩容
 * 1、解决方式
 *    1）new Vector<>()
 *    2）Collections.synchronizedList(new ArrayList<String>())
 *    3）new CopyOnWriteArrayList<>()
 *       写时复制技术
 */
public class Test {
    public static void main(String[] args) {
        //多线程ArrayList不安全
//        List<String> list = new ArrayList<>();
//        解决方式1：
//        Vector<String> list = new Vector<>();
        //解决方式2：
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        //解决方式3：
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
