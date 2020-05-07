package sort;

import java.util.Arrays;

/*
 * 注意事项：
 * 1. 空间换时间，需要的内存大小为：排序数据个数*11*4/1024/1024/1024(G),数据量大时可能出现OutOfMemeryError
 * 2. 此代码不支持负数和小数
 */

public class RadixSort {

	public static void main(String[] args) {
		int[] arr = {53,3,542,748,14,214};
		radixSort(arr);
		System.out.println("基数排序后："+Arrays.toString(arr));
	}

	public static void radixSort(int[] arr) {
		//先得到数组中最大数的位数
		int max = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i]>max) {
				max = arr[i];
			}
		}
		int length = (""+max).length();
		
		int[][] bucket = new int[10][arr.length]; //用于存储排序数据的基数桶
		int[] bucketElementCounts = new int[10]; //用于存储每一轮排序后基数桶的数据量
		int radix = -1;
		for(int i=1,n=1;i<=length;i++,n*=10) {
			//将原始数组按照每一轮（个位/十位/百位等）的基数放入桶中
			for(int j=0;j<arr.length;j++) {
				radix = arr[j]/n%10;
				bucket[radix][bucketElementCounts[radix]]=arr[j];
				bucketElementCounts[radix]++;
			}
			//将桶中的数据按顺序放回原始数组
			int index = 0;
			for(int m=0;m<bucket.length;m++) {
				if(bucketElementCounts[m]!=0) {
					int count = 0;
					while(count!=bucketElementCounts[m]) {
						arr[index++] = bucket[m][count++];
					}
					bucketElementCounts[m]=0;//每一轮后基数桶清空（通过索引的方式）
				}
			}
		}
	}
}
