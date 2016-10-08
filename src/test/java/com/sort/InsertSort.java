package com.sort;

/**
 * 简单插入排序：将带排序元素与排序序列比较
 * @author wangfeiyang
 *
 */
public class InsertSort {
	
	public static void sort(int[] arr){
		if(arr==null||arr.length==0){
			return;
		}
		for(int i=1;i<arr.length;i++){
			int j;int temp=arr[i];
			for(j=i;j>0;j--){
				if(arr[j-1]>temp){
					arr[j]=arr[j-1];
				}else break;
				
			}
			arr[j]=temp;
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
