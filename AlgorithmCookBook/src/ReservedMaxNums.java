import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/10.
 */


public class ReservedMaxNums {


    public static List<Integer> getNums(int n){
        List<Integer> list=new ArrayList<>();
        while (n>10){
            list.add(n%10);
            n=n/10;
        }
        list.add(n);
        return list;

    }
    public static int setNums(List<Integer> list){

        int n=0;
        for(int i=0;i<list.size();i++){
            n=n+list.get(i)*(int)Math.pow(10,i);
        }
        return n;

    }

    public static int solution1(int n,int cnt){
        List<Integer> list=getNums(n);
        list.forEach(x->System.out.print(x+" "));
        int tem=0;
        for(int i=list.size()-1;i>list.size()-cnt-1;i--){
            if(list.get(i)>tem){
                tem=list.get(i);
            }

        }
        System.out.println();
        int li=list.size()-1;
        for(int i=li;i>(li-cnt);i--){
          list.remove(i);

//          System.out.print(i+" ");
        }

        list.add(tem);
        list.forEach(x->System.out.print(x+" "));
        System.out.println();
        return setNums(list);
    }


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int cnt=sc.nextInt();


        System.out.println(solution1(n,cnt));
    }
}
