import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/2.
 */
public class ReverseNums {

    int n=145;
//    char[] s=Integer.toString(n).toCharArray();

    ArrayList arr = new ArrayList();
    public void SplitNum(int n) {
        if(n>10){
            arr.add(n%10);
            n=(int)(n/10);
            SplitNum(n);
        }else {
            arr.add(n);
        }
    }

    public static void main(String[] agrs){
        ReverseNums revNum=new ReverseNums();
        revNum.SplitNum(45187);

        revNum.arr.forEach(
                x->System.out.print(x+"::")
        );
    }




}
