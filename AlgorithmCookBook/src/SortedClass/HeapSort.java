package SortedClass;

import SortedClass.DataWarp;

import java.util.Arrays;

public class HeapSort {
    public static void heapSort(DataWarp[] data){
        System.out.println("Start Sort ...");
        int arrayLength=data.length;
        for(int i=0;i<arrayLength-1;i++){
            builMaxHeap(data,arrayLength-1-i);
            swap(data,0,arrayLength-1-i);
            System.out.println(Arrays.toString(data));
        }
    }

    private static void swap(DataWarp[] data, int i, int j) {
        DataWarp tem=data[i];
        data[i]=data[j];
        data[j]=tem;
    }

    private static void builMaxHeap(DataWarp[] data, int lastIndex) {
        for(int i=(lastIndex-1)/2;i>=0;i--){
            int k=i;
            while(k*2+1<=lastIndex){
                int biggerIndex=2*k+1;
                if(biggerIndex<lastIndex){
                    if(data[biggerIndex].compareTo(data[biggerIndex+1])<0){
                        biggerIndex++;
                    }
                }
                if(data[k].compareTo(data[biggerIndex])<0){
                    swap(data,k,biggerIndex);
                    k=biggerIndex;
                }else{
                    break;
                }
            }
        }
    }


    public static void main(String[] arg){
        DataWarp[] data={
                new DataWarp(21,""),
                new DataWarp(30,""),
                new DataWarp(49,"*"),
                new DataWarp(30,"*"),
                new DataWarp(16,""),
                new DataWarp(9,"")
        };
        System.out.println("排序之前："+ Arrays.toString(data));

        heapSort(data);
        System.out.println("排序之后："+java.util.Arrays.toString(data));
    }
}
