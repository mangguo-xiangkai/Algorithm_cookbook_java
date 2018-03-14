/**
 * Created by windons8 on 2018/3/2.
 */

// LeetCode  OJ 8 String to Integer
    // 字符串转为 整数变量




/*note: 前后的空格
去除前后的空格后 可以 以 + - 0 开头
  非数字字符的处理
  边界 Integer.MAX_VALUE;;Integer.MIN_VALUE
*/
public class Atio {


    public int Solution1(String str){
        char[] chars=str.toCharArray();
        char[] chars1=str.trim().toCharArray(); // 去除 首尾 空格
        Long result=0L;
        int startIndex=0;
        boolean flag=true;

        int length=0;
        for (int i=0;i<chars.length;i++) {
            if (startIndex == i) {
                if (chars[i] == ' ') {
                    startIndex++;
                    continue;
                }
                if (chars[i] == '+' || chars[i] == '0') {
                    continue;
                }
                if (chars[i] == '-') {
                    flag = false;
                    continue;
                }
            }

            if (chars[i] >= '0' && chars[i] <= '9') {
                result = result * 10 + chars[i] - '0';
                length++;
                if (length > 10) {
                    break;
                }
            } else {
                break;
            }
        }
        if(flag) {
            if(result>Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        }else {
            result= -result;
            if(result<Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return result.intValue();
    }






    public static void main(String[] args){
        String cde="dsfdaf";
        System.out.println();
        System.out.println(cde.toCharArray());


        Atio def=new Atio();
        int fg=def.Solution1("4s5515215453983457435");
        System.out.println(fg);
    }



}
