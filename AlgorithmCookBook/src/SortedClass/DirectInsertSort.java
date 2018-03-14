package SortedClass;

import SortedClass.DataWarp;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/18.
 */
public class DirectInsertSort {
//    int [][] a=new int[45][45];
    public static void DirectInsertSort(DataWarp[] data){
        System.out.println("Start Sort ...");

        int arraylength=data.length;
        for(int i=1;i<arraylength;i++){
            DataWarp tem=data[i];
            if(data[i].compareTo(data[i-1])<0){
                int j=i-1;
                for(;j>=0&&data[j].compareTo(tem)>0;j--){
                    data[j+1]=data[j];
                }
                data[j+1]=tem;
            }
            System.out.println(Arrays.toString(data));
        }

    }


    public static void main(String[] arg){
        DataWarp[] data={
                new DataWarp(21,""),
                new DataWarp(30,""),
                new DataWarp(49,""),
                new DataWarp(30,"+"),
                new DataWarp(16,""),
                new DataWarp(9,"")
        };
        System.out.println("排序之前："+ Arrays.toString(data));

        DirectInsertSort(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
