package SortedClass;

import SortedClass.DataWarp;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/18.
 */
public class BinaryInsertSort {
    public static void binaryInsertSort(DataWarp[] data){
        System.out.println("Start Sort ...");
        int arraysLength=data.length;
        for(int i=1;i<arraysLength;i++){
            DataWarp tem=data[i];
            int low=0;
            int high=i-1;
            while (low<=high){
                int mid=(low+high)/2;
                if(tem.compareTo(data[mid])>0){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            for(int j=i;j>low;j--){
                data[j]=data[j-1];
            }
            data[low]=tem;
            System.out.println(Arrays.toString(data));
        }
    }



    public static void main(String[] arg){
        DataWarp[] data={
                new DataWarp(21,""),
                new DataWarp(30,""),
                new DataWarp(49,""),
                new DataWarp(30,""),
                new DataWarp(16,""),
                new DataWarp(9,"")
        };
        System.out.println("排序之前："+ Arrays.toString(data));

        binaryInsertSort(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
