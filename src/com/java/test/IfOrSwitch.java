package com.java.test;

public class IfOrSwitch {
	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		// if语句
		if (a > b) {
			System.out.println("a大于b");
		} else {
			System.out.println("a小于b");
		}

		// switch语句
		switch (a) {
		case 1:
			System.out.println("输出1");
			break;
		case 2:
			System.out.println("输出2");
			break;
		default:
			System.out.println("默认输出");
		}
	}
}
