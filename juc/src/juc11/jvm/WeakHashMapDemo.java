package juc11.jvm;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author sayyes
 * @date 2020/5/23
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
    }

    private static void myHashMap() {
//        HashMap<Integer,String> map = new HashMap<>();
//        {1=我是1}
//        {1=我是1}
//        {1=我是1}	1
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
//        {1=我是1}
//        {1=我是1}
//        {}	0
        Integer key = Integer.valueOf(1);
        String value = "我是1";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map + "\t" + map.size());// WeakHashMap会被垃圾回收
    }
}
