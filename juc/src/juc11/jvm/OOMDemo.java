package juc11.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.VM;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sayyes
 * @date 2020/5/23
 */
public class OOMDemo {
    static class OOMClass { }

    public static void main(String[] args) {
        metaspaceMethod(args);
    }

    private static void metaspaceMethod(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();//spring的动态字节码技术
                enhancer.setSuperclass(OOMClass.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("**********多少次发生异常" + i);
            e.printStackTrace();
        }
    }

    private static void nativeMethod() {
        for (int i = 1; ; i++) {
            System.out.println("**********i=" + i);
            new Thread(() -> {
                //让线程一直存在
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void maxDirectMemoryMethod() {
        System.out.println("配置maxDirectMemory：" + VM.maxDirectMemory());
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }


    private static void gcOverhead() {
        int i = 0;
        List<String> list = new ArrayList();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("********i:" + i);
            e.printStackTrace();
            throw e;
        }
    }

    private static void heapSpaceError() {
        byte[] bytes = new byte[10 * 1024 * 1024];
    }

    private static void stackOverflowErrorDemo() {
        stackOverflowErrorDemo();
    }
}
