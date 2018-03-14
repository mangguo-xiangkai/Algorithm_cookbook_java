package SortedClass;

import SortedClass.DataWarp;
import javax.xml.crypto.Data;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/19.
 */
public class MergeSort {
    public static void mergerSort(DataWarp[] data){
        Sort(data, 0, data.length-1);
    }

    private static void Sort(DataWarp[] data,int left,int right) {
        if(left<right){
            int center=(left+right)/2;
            Sort(data,left,center);
            Sort(data,center+1,right);
            merge(data,left,center,right);
        }
    }

    private static void merge(DataWarp[] data, int left, int center, int right) {
        DataWarp[] temArr=new DataWarp[data.length];
        int mid=center+1;
        int third=left;
        int tem=left;
        while(left<=center&&mid<=right){
            if(data[left].compareTo(data[mid])<=0){
                temArr[third++]=data[left++];
            }else{
                temArr[third++]=data[mid++];
            }
        }
        while (mid<=right){
            temArr[third++]=data[mid++];
        }
        while (left<=center){
            temArr[third++]=data[left++];
        }
        while (tem<=right) {
            data[tem]=temArr[tem++];
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
        mergerSort(data);
        System.out.println("排序之后："+Arrays.toString(data));
    }
}
