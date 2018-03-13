import java.util.ArrayList;
import java.util.List;

/**
 * Created by windons8 on 2018/3/3.
 */

//Leet Code OJ 125  Vliad Palindrome

public class VliadPalindrome {



    public boolean solution1(String s){
        char[] chars=s.toCharArray();

        List<Integer> list=new ArrayList<>();

        int fix='a'-'A';
        for (char c:chars){
            if((c>='a'&&c<='z')||(c>='0'&&c<='9')){
                list.add((int)c);
            }else if(c>='A'&&c<='Z'){
                list.add(c+fix);
            }
        }
        int size=list.size();
        for(int i=0;i<size/2;i++){
            if(list.get(i)!=list.get(size-1-i)){return false;}
        }
        return true;
    }

    public static void main(String[] args){

        System.out.println(new VliadPalindrome().solution1("asdfghgf;dsa"));

    }
}
