package com.sort;

/**
 * 快速排序
 * @author wangfeiyang
 *
 */
public class QuickSort {
	 
	public static void sort(int[] arr,int left,int right){
		int middle,temp;
		int i,j;
		i=left;
		j=right;
		middle=arr[(i+j)/2];
		do{
		while(arr[i]<middle&&i<right){
			i++;//找出左边比中间值大的数
		}
		while(arr[j]>middle&&j>left){
			j--;//找出右边比中间值小的数
		}
		if(i<=j){//将左边大的数和右边小的数进行替换 
			temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
		}while(i<=j);
		if(i<right){
			sort(arr,i,right);
		}
		if(j>left){
			sort(arr,left,j);
		}
		
	}
	public static void main(String[] args) {
		int[] arr=new int[]{11,66,22,0,55,22,0,32};
		sort(arr,0,arr.length-1);
		for(int i:arr){
			System.out.println(i);
		}
	}

}
