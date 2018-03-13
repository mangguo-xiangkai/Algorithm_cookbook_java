import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by windons8 on 2018/3/8.
 */
public class ComputeMaxProduct {

    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        int n=0,k=0,d=0;
        int[] array=null;

        while (cin.hasNextInt()){
            n=cin.nextInt();
            array=new int[n];
            for(int i=0;i<n;i++){
                array[i]=cin.nextInt();
            }
            k=cin.nextInt();
            d=cin.nextInt();
        }
//        cin.close();
//        System.out.println(computeMaxProduct(array,k,d));
        solution1(array,k,d).forEach(x->System.out.print(x+" "));

    }

    public static long computeBestK(int[] array,int k,int d){
        if(array.length==0||k==0||d==0){
            return 0;
        }
        if(array.length==1&&k==1){
            return array[0];
        }
        if(array.length>1&&k>=1){
            long max=Long.MIN_VALUE;

            for(int i=k-1;i<array.length;i++){
                long maxEndByCurrent=computeMaxEndBy(array,k,d,i);
                if(max<maxEndByCurrent)
                    max=maxEndByCurrent;
            }
            return max;
        }else {
            System.out.println("input case error");
            return -1;
        }
    }

    public static long computeMaxEndBy(int[] array,int k,int d,int end){
            if(k==1)
                return array[end];
            long max=Long.MIN_VALUE;

            for(int j=1;j<=d&&(end-j)>=0&&(end-j)>=(k-1-1);j++){
                long res1=array[end]*computeMaxEndBy(array,k-1,d,end-j);
                long res2=array[end]*computeMinEndBy(array,k-1,d,end-j);
                long larger=res1>res2?res1:res2;
                if(max<larger)
                    max=larger;
            }
            return  max;
    }

    private static long computeMinEndBy(int[] array,int k,int d,int end) {
        if (k == 1)
            return array[end];
        long min = Long.MAX_VALUE;

        for (int j = 1; j <= d && (end - j) >= 0 && (end - j) >= k - 1 - 1; j++) {
            long res1 = array[end] * computeMaxEndBy(array, k - 1, d, end - j);
            long res2 = array[end] * computeMinEndBy(array, k - 1, d, end - j);

            long smaller = res1 < res2 ? res1 : res2;

            if (min < smaller)
                min = smaller;
        }
        if (min == Long.MAX_VALUE)
            System.out.println("k:" + k + " d:" + d + " end:" + end);
        return min;
    }


    public static long computeMaxProduct(int[] array, int k ,int d){
        long dpmax[][] = new long[array.length][k+1];
        long dpmin[][] = new long[array.length][k+1];

        for(int i=0;i<array.length;i++){
            dpmax[i][1]=array[i];
            dpmin[i][0]=array[0];
        }

        long maxSoFar=Long.MIN_VALUE;

        for(int j=2;j<=k;j++){
            for (int i=j-1; i<array.length;i++){
                dpmax[i][j]=Long.MIN_VALUE;
                dpmin[i][j]=Long.MAX_VALUE;

                for(int x=1;x<=d&&(i-x)>=j-2;x++){
                    long resMax=max(dpmax[i-x][j-1]*array[i],dpmin[i-x][j-1]*array[i]);
                    long resMin=min(dpmax[i-x][j-1]*array[i],dpmin[i-x][j-1]*array[i]);
                    if(resMax>dpmax[i][j])
                        dpmax[i][j]=resMax;
                    if(resMin<dpmin[i][j])
                        dpmin[i][j]=resMin;
                }
            }
        }
        for(int i=k-1;i<array.length;i++){
            if(dpmax[i][k]>maxSoFar)
                maxSoFar=dpmax[i][k];
        }
        return maxSoFar;
    }



    public static List<Integer> solution1(int[] array,int k, int d){

        if((array.length/d)+1<=k){
            List<Integer> test1=new ArrayList<>();
            List<Integer> test2=new ArrayList<>();
            int i=0;
            int ce=0;
            while (i<array.length){
                int tem=array[i];

                for(int j=i+1;j<i+d&&j<array.length;j++){
                    if(array[j]<=tem){
                        test2.add(array[j]);
                    }else {
                        test2.add(tem);
                        tem=array[j];
                        ce=j;
                    }
                }
                test1.add(tem);
                if(ce==i){
                    i=i+1;
                }else {
                    i=ce;
                }
// i=ce;
            }

            int cha=k-test1.size();
            if(cha>0){
                test2.sort((x1,x2)->(x2-x1));
                for(int c=0;c<cha;c++){
                    test1.add(test2.get(c));
                }
            }
            return test1;

        }else {
            System.out.print("输入不对");
            return null;
        }
    }





}
