package SortedClass;

import SortedClass.DataWarp;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/19.
 */
public class MulitiKeyRedixSort {
    public static void radixSort(int[] data,int radix,int d){
        System.out.println("Start Sorting ...");
        int arrayLength=data.length;
        int[] tem=new int[radix];
        int[] buckets=new int[radix];
        for(int i=0,rate=1;i<d;i++){
            Arrays.fill(buckets,0);
            System.arraycopy(data,0,tem,0,arrayLength);
            for(int j=0;j<arrayLength;j++){
                int subkey=(tem[j]/rate)%radix;
                buckets[subkey]++;
            }
            for(int j=1;j<radix;j++){
                buckets[j]=buckets[j-1]+buckets[j];
            }
            for(int m=arrayLength-1;m>=0;m--){
                int subkey=(tem[m]/rate)%radix;
                data[--buckets[subkey]]=tem[m];
            }
            System.out.println("对 "+rate+" 位上的子关键字排序："+Arrays.toString(data));
            rate*=radix;
        }
    }





    public static void main(String[] arg){
        int[] data={1100,800,500,600,10,21,25,18,98,781};
        System.out.println("排序之前：\n"+(Arrays.toString(data)));
        radixSort(data,10,4);
        System.out.println("排序之后：\n"+Arrays.toString(data));
    }

}
