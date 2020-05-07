package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = {3,5,7,9,11,24};
		int index = binarySearch(arr,0,arr.length-1,1);
		System.out.println(index);
		List list = binarySearch2(arr,0,arr.length-1,13);
		System.out.println(list);
	}

	//找到一个就退出
	public static int binarySearch(int[] arr,int left,int right,int value) {
		//没找到推出递归
		if(left>right) {
			return -1;
		}
		int mid = (left+right)/2;
		if(value<arr[mid]) {
			return binarySearch(arr,left,mid-1,value); //向左递归
		}else if(value>arr[mid]) {
			return binarySearch(arr,mid+1,right,value); //向右递归
		}else {
			return mid; //找到了返回索引
		}
	}
	
	/**
	 * 找到所有的位置
	 * @param arr 查询的数组
	 * @param left 左索引
	 * @param right 右索引
	 * @param value 查找的值
	 * @return 一个列表，存储所有等于查找值的位置索引
	 */
	public static List<Integer> binarySearch2(int[] arr,int left,int right,int value){
		
		// 没找到推出递归
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		if (value < arr[mid]) {
			return binarySearch2(arr, left, mid - 1, value); // 向左递归
		} else if (value > arr[mid]) {
			return binarySearch2(arr, mid + 1, right, value); // 向右递归
		} else {
			List<Integer> list = new ArrayList<Integer>();
			int l=mid-1;
			int r=mid+1;
			while(l>=0 && arr[l]==value) {
				list.add(l--);
			}
			list.add(mid);
			while(r<arr.length && arr[r]==value) {
				list.add(r++);
			}
			return list;
		}
	}
}
