package com.sort;

/**
 * 简单选择排序：每一趟从待排序队列中选取一个最小的记录
 * @author wangfeiyang
 *
 */
public class SelectSort {
	
	public static void sort(int[] arr){
		if(arr==null||arr.length==0){
			return;
		}
		for(int i=0;i<arr.length-1;i++){
			int min=arr[i],t=i;
			for(int j=i;j<arr.length;j++){
				if(arr[j]<min){
					min=arr[j];
					 t=j;
				}
			}
			int temp=0;
			temp=arr[i];
			arr[i]=arr[t];
			arr[t]=temp;			
		}
		
	}
	public static void main(String[] args) {
		int[] arr=new int[]{5,1,3,9,10};
		sort(arr);
		for(int i:arr){
			System.out.println(i);
		}
	}
}
