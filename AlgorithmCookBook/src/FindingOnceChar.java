import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by windons8 on 2018/3/6.
 */
public class FindingOnceChar{

    public static Queue<Character> solution1(String s){
        Map<Character,Integer> charmap=new LinkedHashMap<>();
        if(s==null){return null;}

        char[] chars=s.toCharArray();

        for(char ch:chars){
            if(charmap.containsKey(ch)){
                charmap.put(ch,charmap.get(ch)+1);
            }else {
                charmap.put(ch,1);
            }
        }

        Queue<Character> list=new LinkedList<>();
        for(char de:charmap.keySet()){
            if(charmap.get(de)==1){
                list.add(de);
            }
        }
        return list ;
    }




    public static void main(String[] args){
        FindingOnceChar.solution1("sdfadfasdfafer").forEach(
                x->System.out.println(x)
        );
    }


}
