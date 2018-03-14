import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/10.
 */
public class InterstellerCrossing {
    public static int solution1(int n){
        int h=n/3;
        int nn=0;
        int sc=h;
        if(h*(h+1)<n){
            for(int i=h;i>0;i--){
                int tem=Math.abs(n-h*(h-1));
                if(tem<sc){
                    sc=tem;
                    nn=h;
                }
            }
        }else {
            return -1;
        }
        return -1;
    }


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        System.out.println(solution1(n));

    }
}
