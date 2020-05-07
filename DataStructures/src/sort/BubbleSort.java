package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {
//		int[] arr2 = {6,5,4,3,2,1};
//		int[] arr = {6,15,24,33,42,51};
		
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str1 = simpleDateFormat.format(date1);
		System.out.println("排序前时间："+date_str1);
		
		int temp = 0;
		Boolean flag = false;
		//将序列从小到大排序，第一层循环表示排序多少趟，第二层表示每一趟需要比较到哪一位结束
		//每一趟都是把最大交换到最后
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {//如果改成<就是从大到小排序
					temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
					flag = true;
				}
			}
//			System.out.printf("第 %d 趟排序的结果为:%s\n",i+1,Arrays.toString(arr));
			if(!flag) {
				break; //如果这一趟一次都没有交换，证明已经有序，无需进行下一趟
			}else {
				flag = false;
			}
		}
		
		Date date2 = new Date();
		String date_str2 = simpleDateFormat.format(date2);
		System.out.println("排序后时间："+date_str2);
	}

}
