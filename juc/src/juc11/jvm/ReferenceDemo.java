package juc11.jvm;

/**
 * @author sayyes
 * @date 2020/5/23
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        Object object1 = new Object();//强引用
        Object object2 = object1;//object2引用赋值
        object1 = null;//改变object1的值
        System.gc();//垃圾回收object1,不会回收Object2
        System.out.println(object1);
        System.out.println(object2);
    }
}
