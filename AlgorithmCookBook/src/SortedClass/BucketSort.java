package SortedClass;

import SortedClass.DataWarp;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/19.
 */
public class BucketSort {
    public static void bucketSort(DataWarp[] data,int min,int max){
        System.out.println("Start Sort ...");
        int arraryLength=data.length;
        DataWarp[] tem=new DataWarp[arraryLength];
        int[] buckets=new int[max-min];
        for(int i=0;i<arraryLength;i++) {
            buckets[data[i].data-min]++;//
        }
        System.out.println(Arrays.toString(buckets));
        for(int i=1;i<max-min;i++){
            buckets[i]=buckets[i]+buckets[i-1];
        }
//        for()
        System.out.println(Arrays.toString(buckets));
        System.arraycopy(data,0,tem,0,arraryLength);

        for(int k=arraryLength-1;k>=0;k--){
            data[--buckets[tem[k].data-min]]=tem[k];
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
        bucketSort(data,9,50);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
