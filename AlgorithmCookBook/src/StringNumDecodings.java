import java.util.HashMap;
import java.util.Map;

/**
 * Created by windons8 on 2018/3/2.
 */


// LeetCode OJ 91  decode Ways



/* 一条消息 由 A-Z  组成
  'A'->1;
  'B'->2
  ...
  'Z'->26

  例如 ：  给你一条消息  “12” 可以被 'AB' 或 'L' ；则 这条消息的 解码 数 为2；

  求： 给定 一条消息后， 算出可能的解码 数量；
    */
public class StringNumDecodings {

    public int numDecodings1(String s){
        Map<String,Integer> resMap=new HashMap<>();
        char[] chars=s.trim().toCharArray();
        int len=chars.length;
        if(len==0){return 0;}
        Integer res1=0;
        Integer res2=0;

        if (len >= 1) {
            char ch=chars[0];
            if('A'<=ch&&ch<='Z'){
                throw new RuntimeException("字符不满足要求");
            }else {
                if(len==1){
                    return 0;
                }else {
                    String substring=s.substring(1);
                    res1=resMap.get(substring);
                    if(res1==null){
                        res1=numDecodings1(substring);
                        resMap.put(substring,res1);
                    }
                }
            }
            if(len>=2) {
                if(ch=='1'){
                    if(len==2){
                        res2=1;
                    }else {
                        res2=numDecodings1(s.substring(2));
                    }
                }else if(ch=='2'){
                    if(chars[1]>='0'&&chars[1]<='6'){
                        if(len==2){
                            res2=1;
                        }else {
                            String substring=s.substring(2);
                            res2=resMap.get(substring);
                            if(res2==null){
                                res2=numDecodings1(substring);
                                resMap.put(substring,res2);
                            }
                        }
                    }
                }
            }
        }
        return res1+res2;
    }


    public int numDecodings2(String s){
        if(s==null||s.length()==0) {return 0;}

        char[] sa=s.toCharArray();

        int[] nums=new int[sa.length+1];

        nums[0]=1;

        for(int i=1;i<=sa.length;i++){
            if(sa[i-1]!='0') nums[i]+=nums[i-1];
            if(i>1&& sa[i-2]=='1')nums[1]+=nums[i-2];
            if(i>1&&sa[i-2]=='2'&& sa[i-1]>='0'&&sa[i-1]<='6') nums[i]+=nums[i-2];
        }


        return nums[sa.length];

    }

   public static void  main(String[] args){
        StringNumDecodings sND=new StringNumDecodings();

//        System.out.println(sND.numDecodings1("AD"));
        System.out.println(sND.numDecodings2("ADCAL"));

   }
}












