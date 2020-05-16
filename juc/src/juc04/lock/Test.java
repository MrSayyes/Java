package juc04.lock;


import java.util.concurrent.TimeUnit;

/**
 * 1、标准访问（双synchronized），请问先打印邮件还是短信：打印邮件
 * 2、暂停4s在邮件方法，先邮件还是短信：先邮件后短信
 * 3、新增普通sayHello方法，先邮件还是hello：先hello
 * 4、两部手机，是先打印邮件还是短信：先短信
 * 5、一个手机，两个静态同步方法：先邮件
 * 6、两个手机，两个静态同步方法：先邮件
 * 7、一个静态同步方法，一个普通同步方法，同一部手机：先短信
 * 8、一个静态同步方法，一个普通同步方法，两部部手机：先短信
 * 
 * 总结：普通synchronized锁对象
 *      静态同步方法,锁当前类的Class对象
 * @author sayyes
 * @date 2020/5/3
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new  Thread(()->{
            try {
//                phone.sendSMS();//案例1,案例5，案例7
//                phone.sayHello();//案例3
                phone2.sendSMS();//案例4，案例6，案例8
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

/**
 * 资源类
 */
class Phone{
    public  static synchronized  void  sendEmail() throws  Exception{
        //暂停4s,案例2
        TimeUnit.SECONDS.sleep(4);
        System.out.println("******sendEmail");
    }

    public  synchronized  void  sendSMS() throws  Exception{
        System.out.println("******sendSMS");
    }

    public void sayHello(){
        System.out.println("*******sayHello");
    }
}