/**
 * Created by windons8 on 2018/3/2.
 */
public class MagicCoins {


    public StringBuffer solution1(int n,StringBuffer str){
//        StringBuffer str=new StringBuffer();
        if(n==1){
            str.append("1");
            return str;
        }

        if(n==2){
            str.append("2");
            return str;
        }
        if(n%2==0){
            str.append("2");
            solution1((n-2)/2,str);
        }else {
            str.append("1");
            solution1((n-1)/2,str);
        }
        return str;
    }




    public static void  main(String[] args){

        StringBuffer strB=new StringBuffer();

        MagicCoins mC=new MagicCoins();

        mC.solution1(100,strB);

        System.out.println(strB.reverse());

    }



}
