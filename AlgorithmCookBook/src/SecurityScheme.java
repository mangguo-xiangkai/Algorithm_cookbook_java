import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/10.
 */
public class SecurityScheme {

    public static int solution1(int[] data){


        int cout=data.length-1;
        for(int i=0;i<data.length-2;i++){

            for(int j=i+2;j<data.length;j++){
                int max=Math.max(data[i],data[j]);
                boolean flag=true;
                for(int k=i+1;k<=j;k++){
                    if(data[k]>max){
                        flag=false;
                    }
                }
                if(flag==true){
                    cout+=1;
                }
            }
        }
        return cout;
    }




    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int[] data=new int[n];
        for(int i=0;i<n;i++){
            data[i]=sc.nextInt();
        }
        System.out.println(solution1(data));
    }
}
