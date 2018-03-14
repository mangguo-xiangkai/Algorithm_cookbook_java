import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by windons8 on 2018/3/4.
 */
public class PrimeNumbers {

    public static ArrayList findPrimes(final int max){
        int[] prime=new int[max+1];
        ArrayList list=new ArrayList();
        for(int i=2;i<=max;i++){
            prime[i]=1;
        }
        for(int i=2;i*i<=max;i++){
            if(prime[i]==1){
                for(int j=2*i;j<max;j++){
                    if(j%i==0) prime[j]=0;
                }
            }
        }

        for(int i=2;i<max;i++){
            if(prime[i]==1){
                list.add(i);
            }
        }
//        int[] p=new int[list.size()];
//        Object[] obj=list.toArray();
//        for(int i=0;i<p.length;i++){
//            p[i]=((Integer)obj[i]).intValue();
//        }

        return list;
    }




    public static void main(String[] args){
        ArrayList prime=PrimeNumbers.findPrimes(100);
        prime.forEach(x-> System.out.println(x));
//        for(int i=0;i<prime.length;i++){
//            System.out.println(prime[i]+" ");
//        }
//        for(int i=1;i<=10000;i++){
//            if((int)(Math.random()*52)==52)
//                System.out.println("true");
//        }
    }
}
