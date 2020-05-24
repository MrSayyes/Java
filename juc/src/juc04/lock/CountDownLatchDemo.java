package juc04.lock;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * @author sayyes
 * @date 2020/5/17
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                latch.countDown();//计数减一
            }, CountryEnum.contryForEach(i).getValue()).start();
        }
        latch.await();//计数到0就不阻塞
        System.out.println("******秦灭6国，一统华夏");
    }
}

@Getter
enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");
    private int key;
    private String value;

    CountryEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static CountryEnum contryForEach(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (index == value.key) {
                return value;
            }
        }
        return null;
    }
}

