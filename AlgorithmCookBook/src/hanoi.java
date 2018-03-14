import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by windons8 on 2018/2/11.
 */
public class hanoi {
    public static void main(String[] arg) throws IOException{
        int n;
        BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入盘子的数目：");
        n= Integer.parseInt(buf.readLine());
        hanoi hanoi=new hanoi();
        hanoi.move(n,"A","B","C");
    }
    public void move(int n,String a,String b,String c){
        if( n==1){
            System.out.println("盘"+n+"由"+a+"移至"+c);
        } else{
            move(n-1,a,c,b);
            System.out.println("盘"+n+"由"+a+"移至"+c);
            move(n-1,b,a,c);
        }
    }
}
