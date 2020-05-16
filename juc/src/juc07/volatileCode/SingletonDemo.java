package juc07.volatileCode;

/**
 * @author sayyes
 * @date 2020/5/13
 */
public class SingletonDemo {
    //添加volatile，防止DCL模式代码的多线程不安全。
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "构造方法");
    }

    //    //加synchronized锁整个代码，太重了呢
////    public static synchronized SingletonDemo getInstance() {
//    public static SingletonDemo getInstance() {
//        if (instance == null) {
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }
    //DCL模式（double check lock双端检索机制）
    //双端检索机制不一定线程安全，因为有指令重排的存在，加入volatile禁止指令重排

    /**
     * **重排说明**
     * 正常流程：1、分配对象内存；2、初始化对象；3、设置instance指向刚分配的内存地址，此时instance!=null
     * 重排情况：1、分配对象内存；2、设置instance指向刚分配的地址，此时instance不为空，但是初始化未完成；3、初始化对象
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {
            //同步块前和同步块后都进行if判断
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);

        //多线程不是单例了呢，加synchronized可以实现，但是不合适
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
