package com.java.test;

import java.util.Scanner;

public class StringDemo {

	public static void main(String[] args) {
		reverse();
//		string2Array();
	}
	public static void reverse() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请录入:");
		String s = scanner.nextLine();
		//可以循环
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		System.out.println(sb);
	}
	//录入字符串以，分隔展示
	public static void string2Array() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请录入:");
		String s = scanner.nextLine();
		String outString = "[";//这里可以使用StringBuiler
		for (int i = 0; i < s.length(); i++) {
			if (i== s.length()-1) {
				outString+=s.charAt(i);
			}else {
				outString+=s.charAt(i)+", ";
			}
		}
		outString+="]";
		System.out.println(outString);
	}
	
	//获取录入值里面的大小写，数字的个数
	public static void getNo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请录入：");
		String line = scanner.nextLine();
		int a=0;
		int b=0;
		int c=0;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if(ch>='A' && ch<='Z') {
				a++;
			}
			if(ch>='a' && ch<='z') {
				b++;
			}
			if(ch>='0' && ch<='9')
			c++;
		}
		System.out.println("大写："+a);
		System.out.println("小写："+b);
		System.out.println("数字："+c);
	}
}

