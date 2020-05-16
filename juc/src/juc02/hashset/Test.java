package juc02.hashset;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author sayyes
 * @date 2020/4/29
 */
public class Test {
    public static void main(String[] args) {
        //HashSet底层是HashMap实现,但是HashMap是键值对，为啥set不是键值对
//        HashSet在add的时候value给了一个object的常量
//        private static final Object PRESENT = new Object();
//        public boolean add(E e) {
//            return map.put(e, PRESENT)==null;
//        }
//        HashSet<Object> set = new HashSet<>();//java.util.ConcurrentModificationException
        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
