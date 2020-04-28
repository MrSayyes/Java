package com.java.test;

public class ForOrWhile {
	public static void main(String[] args) {
		// 循环输出1，2，3，4，5
		// for循环
		for (int i = 1; i < 6; i++) {
			System.out.println("for循环：" + i);
		}

		// while
		int w = 1;
		while (w < 6) {
			System.out.println("while循环：" + w);
			w++;
		}

		// do...while
		int dw = 1;
		do {
			System.out.println("do...while循环：" + dw);
			dw++;
		} while (dw < 6);

		// for-each(增强for)
		int[] arr = { 1, 2, 3, 4, 5 };
		for (int x : arr) {
			System.out.println("增强for：" + x);
		}

		// continue
		for (int i = 1; i < 6; i++) {
			if (i == 2) {
				continue;// 不输出2
			}
			System.out.println("continue跳转：" + i);
		}

		// break
		for (int i = 1; i < arr.length; i++) {
			if (i == 2) {
				break;// 跳出循环体
			}
			System.out.println("break跳转：" + i);
		}
	}
}
