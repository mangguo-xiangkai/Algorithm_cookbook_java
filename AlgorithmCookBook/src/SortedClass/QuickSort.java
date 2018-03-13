package SortedClass;

import SortedClass.DataWarp;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/18.
 */
public class QuickSort {
    private static void swap(DataWarp[] data, int i, int j) {
        DataWarp tem=data[i];
        data[i]=data[j];
        data[j]=tem;
    }

    private static void subsort(DataWarp[] data,int start,int end){
        if(start<end){
            DataWarp base=data[start];
            int i=start;
            int j=end+1;
            while (true){
                while (i<end&&data[++i].compareTo(base)<=0);
                while (j>start&&data[--j].compareTo(base)>=0);
                if(i<j){
                    swap(data,i,j);
                }else{
                    break;
                }
            }
            swap(data,start,j);
            subsort(data,start,j-1);
            subsort(data,j+1,end);
        }
    }


    public static void quickSort(DataWarp[] data){
        subsort(data,0,data.length-1);
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
        quickSort(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
