package juc11.jvm;

import java.lang.ref.WeakReference;

/**
 * @author sayyes
 * @date 2020/5/23
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> o2 = new WeakReference<>(o1);//弱引用
        System.out.println(o1);
        System.out.println(o2.get());//get获取引用

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(o2.get());//弱引用被回收
    }
}
