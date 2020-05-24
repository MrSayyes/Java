package basic;

import java.util.Arrays;
import java.util.List;

public class ArrayDemo {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		// for遍历
		for (int i = 0; i < arr.length; i++) {
			System.err.println("for遍历" + arr[i]);
		}

		// for-each遍历
		for (int x : arr) {
			System.out.println("for-each遍历" + x);
		}

		// 获取最小值
		int[] array = { 19, 26, 5, 7, 9, 11 };
		int min = array[0];
		for (int i = 0; i < array.length; i++) {
			if (min > array[i]) {
				min = array[i];
			}
		}
		System.out.println("最小值：" + min);

		// 数组转list
		String[] strArr = {"1","2"};
        List list = Arrays.asList(strArr);
		System.out.println("list长度："+list.size());

		//数组转String
		String s = Arrays.toString(strArr);
		System.out.println(s);
	}
}
