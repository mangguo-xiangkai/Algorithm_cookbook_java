package SortedClass;

import SortedClass.DataWarp;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/18.
 */
public class BubbleSort {
    public static void bubbleSort(DataWarp[] data){
        System.out.println("Start Sort ...");
        int arrayLength=data.length;
        for(int i=0;i<arrayLength-1;i++){
            boolean flag=false;
            for(int j=0;j<arrayLength-1-i;j++) {
                if (data[j].compareTo(data[j+1]) > 0) {
                    DataWarp tem = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = tem;
                    flag = true;
                }
            }
            System.out.println(Arrays.toString(data));
            if(!flag){
                break;
            }
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
//        (data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
