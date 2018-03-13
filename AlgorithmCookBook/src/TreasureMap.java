import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/10.
 */
public class TreasureMap {

    public static String solution1(char[] s,char[] ce){
        int j=0;
        for(int i=0;i<s.length;i++){
            if(ce[j]==s[i]){
                if(j<=ce.length){
                    return "Yes";
                }
                j++;
            }
        }
        return "No";
    }



    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        char[] s=sc.nextLine().toCharArray();
        char[] ce=sc.nextLine().toCharArray();

        System.out.println(solution1(s,ce));
    }
}
