import java.util.Arrays;
import java.util.StringTokenizer;

import static jdk.internal.org.objectweb.asm.Type.getType;

/**
 * Created by windons8 on 2018/2/11.
 *
 * 对字串符的的数字提取出来 并排序输出
 *
 */
public class ByComma {
    public static  String[] splitStringByComma(String source){
        if (source==null||source.trim().equals(" "))
            return null;
        StringTokenizer commaToker =new StringTokenizer(source,",");
        String[] result=new String[commaToker.countTokens()];
        int i=0;

        while (commaToker.hasMoreTokens()){
            result[i]=commaToker.nextToken();
            i++;
        }

        System.out.println("::"+getType(result[2]));
        return result;

    }

    public static void main(String[] arg){
        String[] s =splitStringByComma("5,8,7,98,56,2,3,4");
        int[] ii=new int[s.length];
        for (int i=0;i<s.length;i++){
            ii[i]=Integer.parseInt(s[i]);
        }
        Arrays.sort(ii);
        for(int i =0;i<s.length;i++){
            System.out.println(ii[i]);
        }
//        for (int i=(s.length-1);i>=0;i--){
//            System.out.println(ii[i]);
//        }
    }
}

