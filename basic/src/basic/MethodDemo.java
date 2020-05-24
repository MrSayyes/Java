package basic;

public class MethodDemo {
	// 主函数
	public static void main(String[] args) {
		// 方法调用
		newMethod();
		newMethod("hello");
	}

	// 无参方法定义
	public static void newMethod() {
		System.out.println("无参方法！");
	}

	// 有参方法定义
	public static void newMethod(String s) {
		System.out.println("有参方法" + s);
	}
}
