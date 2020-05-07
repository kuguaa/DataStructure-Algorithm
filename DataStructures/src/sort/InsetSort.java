package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsetSort {

	public static void main(String[] args) {
		//int[] arr = {6,5,4,3,2,1};
		//创建一个长度为 80000 的随机数组
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str1 = simpleDateFormat.format(date1);
		System.out.println("排序前时间："+date_str1);
		
		inserSort(arr);
//		System.out.println(Arrays.toString(arr));
		
		Date date2 = new Date();
		String date_str2 = simpleDateFormat.format(date2);
		System.out.println("排序后时间："+date_str2);
	}

	public static void inserSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0;
		for(int i=1;i<arr.length;i++) {
			insertVal = arr[i];
			insertIndex = i-1;
			while(insertIndex>=0 && insertVal<arr[insertIndex]) {//从小到大排序，如果改成>号，就是从大到小排序
				arr[insertIndex+1]=arr[insertIndex];
				insertIndex--;
			}
//			if(insertIndex+1!=i) {
//				arr[insertIndex+1]=insertVal;
//			}
			arr[insertIndex+1]=insertVal;
		}
	}
}
