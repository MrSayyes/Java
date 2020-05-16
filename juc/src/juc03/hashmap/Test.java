package juc03.hashmap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sayyes
 * @date 2020/4/29
 */
public class Test {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();//java.util.ConcurrentModificationException
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
