import java.util.Arrays;

/**
 * Created by windons8 on 2018/3/3.
 */
public class RegularExpressionMatching {

    public boolean solution1(String s, String p){
        boolean[] match=new boolean[s.length()+1];
        Arrays.fill(match,false);
        match[s.length()]=true;
        for(int i=p.length()-1;i>=0;i--) {
            if (p.charAt(i) == '*') {
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j] || match[j + 1] && (p.charAt(i - 1) == '.' || s.charAt(j) == p.charAt(i - 1));
                }
                i--;
            } else {
                for (int j = 0; j < s.length(); j++) {
                    match[j]=match[j+1]&&(p.charAt(i)=='.'||p.charAt(i)==s.charAt(j));
                }
                match[s.length()]=false;
            }
        }
        return match[0];
    }

    public static void main(String[] args){
        RegularExpressionMatching reMa=new RegularExpressionMatching();
        System.out.print(reMa.solution1("aab","c*a*b"));
    }


}
