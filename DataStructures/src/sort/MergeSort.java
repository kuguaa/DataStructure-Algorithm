package sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int arr[] = {8,4,5,7,1,3,6,2};
		int[] temp = new int[arr.length];
		mergeSort(arr,0,arr.length-1,temp);
		System.out.println("归并排序后："+Arrays.toString(arr));
	}
	
	//分+合
	public static void mergeSort(int[] arr,int left,int right,int[] temp) {
		if(left<right) {
			int mid = (left + right)/2;
			//向左递归分解
			mergeSort(arr,left,mid,temp);
			//向右递归分解
			mergeSort(arr,mid+1,right,temp);
			merge(arr,left,mid,right,temp);
		}
	}

	/**
	 * 合并的方法
	 * @param arr 排序的原始数组
	 * @param left 左边有序序列的初始索引
	 * @param mid 中间索引
	 * @param right	右边索引
	 * @param temp 中转数组
	 */
	public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
		int i=left; //左边有序序列的初始索引
		int j=mid+1; //右边有序序列的初始索引
		int t=0; //中转数组的索引
		
		//1. 把左右两边的有序数组合并成一个有序数组，存入中转数组（方法同合并两个有序链表，不改变原始链表生成新链表）
		while(i<=mid && j<=right) {
			if(arr[i]<=arr[j]) {
				temp[t]=arr[i];
				t++;
				i++;
			}else {
				temp[t]=arr[j];
				t++;
				j++;
			}
		}
		
		while(i<=mid) {
			temp[t]=arr[i];
			t++;
			i++;
		}
		
		while(j<=right) {
			temp[t]=arr[j];
			t++;
			j++;
		}
		
		//2. 将中转数组复制到原数组中
		i=left;
		t=0;
		while(i<=right) {
			arr[i]=temp[t];
			i++;
			t++;
		}
	}
}
