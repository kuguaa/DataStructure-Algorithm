package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {
//		 int[] arr = {-9,78,0,23,-567,70,3,-4,5,-7};
		// 创建一个长度为 80000 的随机数组
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 800000);
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str1 = simpleDateFormat.format(date1);
		System.out.println("排序前时间：" + date_str1);

		quickSort(arr,0,arr.length-1);
//		System.out.println(Arrays.toString(arr));

		Date date2 = new Date();
		String date_str2 = simpleDateFormat.format(date2);
		System.out.println("排序后时间：" + date_str2);
	}

	public static void quickSort(int[] arr,int left,int right) {
		int l = left;
		int r = right;
		int pivot = arr[(left+right)/2];//原数组位置在中间的值
		int temp=0;
		while(l<r) {
			//从左到右找到不小于中值的位置
			while(arr[l]<pivot) {
				l++;
			}
			//从右到左找到不大于中值的位置
			while(arr[r]>pivot) {
				r--;
			}
			//如果 l>=r 说明本轮以排序完成（比中值小的都在左边，比中值大的都在右边）
			if(l>=r) {
				break;
			}
			//找到了未排序的则交换
			temp = arr[r];
			arr[r] = arr[l];
			arr[l] = temp;
			//位移	
			l++;
			r--;
			
		}
		
		//如果l==r,必须 l++,r--,否则会出现栈溢出
		if(l==r) {
			l++;
			r--;
		}
		
		//向左递归
		if(left<r) {
			quickSort(arr,left,r);
		}
		
		//向右递归
		if (right > l) {
			quickSort(arr, l, right);
		}
	}
}
