import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/10.
 */
public class SplitApple {

    public static int solution1(int sum,int[] data){
        int n=data.length;
        int cout=0;
        if(sum%n==0){
            return -1;
        }else {
            int avg=sum/n;
            for(int i=0;i<n;i++){
                if((data[i]-avg)%2!=0)
                    return -1;
                if(data[i]>avg)
                    cout=cout+(data[1]-avg)/2;
            }
            return cout;
        }

    }




    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        int n;
        n=sc.nextInt();

        int[] d=new int[n];
        int sum=0;
        for(int i=0;i<n;i++){
            d[i]=sc.nextInt();
            sum=sum+d[i];
        }

       System.out.println(solution1(sum,d));
    }
}
