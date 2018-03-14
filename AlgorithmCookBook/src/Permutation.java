/**
 *
 *排列组合数字
 * Created by windons8 on 2018/2/11.
 */
public class Permutation {

    public static void perm(int[] num,int threshold){
        if(threshold<num.length-1){
            for (int j=threshold;j<=num.length-1;j++){
                int tmp=num[j];
                for (int k=j;k>threshold;k--)
                    num[k]=num[k-1];
                num[threshold]=tmp;
                perm(num,threshold+1);
                for (int k=threshold;k<j;k++)
                    num[k]=num[k+1];
                num[j]=tmp;
            }
        }else {
            for (int j=1;j<=num.length-1;j++)
                System.out.print(num[j]+" ");
            System.out.println();
        }
    }

    public static void main(String[] arg){
        int[] num=new int[]{1,2,3,4};
        perm(num,0);
    }
}
