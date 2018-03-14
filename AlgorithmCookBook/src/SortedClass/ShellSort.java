package SortedClass;

import SortedClass.DataWarp;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/19.
 */
public class ShellSort {
    public static void shellSort(DataWarp[] data){
        System.out.println("Start sort...");
        int arrayLength=data.length;
        int h=1;
        while (h<=arrayLength/3){
            h=h*3+1;
        }
        while (h>0){
            System.out.println("=== h的值："+h+" ====");
            for (int i=h;i<arrayLength;i++){
                DataWarp tem=data[i];
                if(data[i].compareTo(data[i-h])<0){
                    int j=i-h;
                    for(;j>=0&&data[j].compareTo(tem)>0;j-=h){
                        data[j+h]=data[j];
                    }
                    data[j+h]=tem;
                }
                System.out.println(Arrays.toString(data));
            }
            h=(h-1)/3;
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
        shellSort(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
