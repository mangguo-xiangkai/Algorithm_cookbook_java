/**
 * Created by windons8 on 2018/3/3.
 */
public class strStr {






    private int[] getNext(String s){
        int[] next=new int[s.length()];
        next[0]=0;
        if(s.length()==1){
            return next;
        }
        next[1]=0;
        int j=1;
        int i=0;
        while (i<s.length()-1){
            if(s.charAt(i)==s.charAt(j)) {
                i++;
                j++;
                if (s.charAt(i) == s.charAt(j)) {
                    next[i] = next[j - 1];
                } else {
                    next[i] = j;
                }
            }else {
                if(j==0){
                    i++;
                    next[i]=0;
                }else {
                    j=next[j];
                }
            }
        }
        return next;
    }

    public int solution1(String haystack,String needle){
        //  KMP   字符串匹配 算法

        if(needle.length()==0){
            return 0;
        }
        int[] next=getNext(needle);
        int index=-1;
        int i=0;
        int j=0;
        while (i<haystack.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                if(j==needle.length()-1){
                    return i-j;
                }
                i++;
                j++;
            }else{
                if(j==0){
                    i++;
                }else {
                    j=next[i];
                }
            }
        }
        return index;
    }


    public static  void main(String[] args){
        strStr s1=new strStr();
        System.out.println(s1.solution1("sfasdff","sdf"));

    }









}
