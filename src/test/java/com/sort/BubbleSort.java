package com.sort;
/**
 * 简单冒泡排序
 * @author wangfeiyang
 *
 */
public class BubbleSort {
	
	public static void sort(int[] arr){
		if(arr==null||arr.length==0){
			return;
		}
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-(1+i);j++){
				int temp=0;
				if(arr[j]>arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
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
