/**
 * Created by windons8 on 2018/3/3.
 */
public class ClimbStairs {

    public int solution1(int n){
        //  通过 手推 发现  其实 斐波那契  数列
        if(n<=2){return n;}
        int pre=1,src=2;
        for(int i=3;i<=n;i++){
            int tem=src;
            src=src+pre;
            pre=tem;
        }
        return pre;
    }

    public int solution2(int n){
        int jump=0,maxJump=2;
        if(n<2){return n;}
        for(int i=1;i<=maxJump;i++){
            jump+=solution2(n-i);
        }
        return jump;
    }


    public static void main(String[] args){
        ClimbStairs cliSt=new ClimbStairs();

        for (int j=0;j<10;j++){
            System.out.println("当有 "+j+" 个楼梯时，有 "+cliSt.solution1(j)+" 种解法；");
        }
        System.out.println(cliSt.solution2(7));


    }
}
