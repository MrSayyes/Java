package juc08.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 理解原子引用
 *
 * @author sayyes
 * @date 2020/5/15
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zs", 3);
        User ls = new User("ls", 5);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);
        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());
    }
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class User {
    String userName;
    int age;
}
