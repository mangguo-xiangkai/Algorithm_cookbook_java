package SortedClass;

import SortedClass.DataWarp;

import javax.xml.crypto.Data;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/18.
 */



public class SelectSort {
    public static void selectSort1(DataWarp[] data){
        System.out.println("Start Sorting ...");
        int arrayLength=data.length;
        for(int i=0;i<arrayLength-1;i++){
            for(int j=i+1;j<arrayLength;j++){
                if (data[i].compareTo(data[j])>0) {
                    DataWarp tmp=data[i];
                    data[i]=data[j];
                    data[j]=tmp;
                }
            }
            System.out.println(java.util.Arrays.toString(data));
        }
    }
    public static void selectSort2(DataWarp[] data){
        System.out.println("Start Sorting ...");
        int arrayLenth=data.length;
        for(int i=0;i<arrayLenth-1;i++){
            int minindex=1;
            for (int j=i+1;j<arrayLenth;j++){
                if(data[minindex].compareTo(data[i])>0)
                    minindex=j;
            }
            if(minindex!=i){
                DataWarp tmp=data[minindex];
                data[i]=data[minindex];
                data[minindex]=tmp;
            }
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
        selectSort1(data);
//        selectSort2(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
