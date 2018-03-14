import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/7.
 */
public class MaxAbility {



    private int[] getInIndex(int[] data,int start,int end){
        return Arrays.copyOfRange(data,start,end);
    }


    public Map<Integer,Integer> solution1(int[] data,int span,int nums,int k){

        return null;
//        Set<Integer> test=new TreeSet<>();
//        List<Integer> list2=new ArrayList<>();
//        int[] temp=new int[nums];
//        ArrayList list=new ArrayList();
//        if((nums/span)+1<=k) {
//            int duo=(int)(nums/span)+1-k;
//            for (int i = 0; i < data.length; i += span) {
//                int[] tem;
//                if (i + span < data.length) {
//                    tem = Arrays.copyOfRange(data, i, i + span);
//                } else {
//                    tem = Arrays.copyOfRange(data, i, data.length);
//                }
//                int tmp = tem[0];
//                for (int d : tem) {
//                    if (d > tmp) {
//                        tmp = d;
//                    } else {
//                        temp[duo];
//                    }
//                }
//                list.add(tmp);
//            }
//        }
//Arrays.sort();

    }


    public static int getMaxPower(int n,int[] a,int k,int d){
        int[][] dmax=new int[k+1][n+1];
        int[][] dmin=new int[k+1][n+1];

        int maxPower=Integer.MIN_VALUE;
        for (int i=1;i<=n;i++) {
            dmax[1][i]=a[i];
            dmin[1][i]=a[i];

            for(int stuNum=2;stuNum<=k;stuNum++){
                for(int j=i-1;j>0&&i-j<=d;j--){
                    dmax[stuNum][i]=Math.max(dmax[stuNum][i],Math.max(dmax[stuNum-1][j]*a[i],dmin[stuNum-1][j]*a[i]));
                    dmin[stuNum][i]=Math.min(dmin[stuNum][i],Math.min(dmin[stuNum-1][j]*a[i],dmax[stuNum-1][j]*a[i]));
                }
            }
            maxPower=Math.max(dmax[k][i],maxPower);
        }
        return maxPower;
    }



    public static void  main(String[] args){
        Scanner scanner=new Scanner(System.in);

        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int[] a=new int[n+1];
            for(int i=1;i<=n;i++){
                a[i]=scanner.nextInt();
            }
            int k=scanner.nextInt();
            int d=scanner.nextInt();
            System.out.println(getMaxPower(n,a,k,d));
        }
    }


}
