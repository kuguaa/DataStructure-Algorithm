package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {
	// int[] arr = {6,5,4,3,2,1};
		// 创建一个长度为 80000 的随机数组
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 800000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str1 = simpleDateFormat.format(date1);
		System.out.println("排序前时间：" + date_str1);

		shellSort(arr);
//		System.out.println(Arrays.toString(arr));

		Date date2 = new Date();
		String date_str2 = simpleDateFormat.format(date2);
		System.out.println("排序后时间：" + date_str2);
	}

	public static void shellSort(int[] arr) {
		//用增量 gap 进行分组，并逐步缩小增量
		for(int gap=arr.length/2;gap>0;gap/=2) {
			//从第 gap 个元素，逐个对其所在组进行直接插入排序
			for(int i=gap;i<arr.length;i++) {
				int j=i;
				int temp = arr[i];
				if(arr[j-gap]>temp) {
					while(j-gap>=0 && arr[j-gap]>temp) {
						arr[j] = arr[j-gap];
						j -= gap;
					}
					arr[j]=temp;
				}
			}
		}
	}
}
