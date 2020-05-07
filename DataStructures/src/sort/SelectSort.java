package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
//		int[] arr = {6,5,4,3,2,1};
		
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str1 = simpleDateFormat.format(date1);
		System.out.println("排序前时间："+date_str1);
		
		selectSort(arr);
//		System.out.println(Arrays.toString(arr));
		
		Date date2 = new Date();
		String date_str2 = simpleDateFormat.format(date2);
		System.out.println("排序后时间："+date_str2);
	}

	public static void selectSort(int[] arr) {
		int min = 0;
		int minIndex = 0;
		for(int i=0;i<arr.length-1;i++) {
			min = arr[i];
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]<min) {//从小到大排序，如果改成>号，就是从大到小排序
					minIndex = j;
				}
			}
			if(minIndex!=0) {
				min = arr[minIndex];
				arr[minIndex] = arr[i];
				arr[i] = min;
				minIndex = 0;
			}
		}
	}
}
