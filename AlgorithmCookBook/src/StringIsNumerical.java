/**
 * Created by windons8 on 2018/3/6.
 */
public class StringIsNumerical {


    public static boolean solution1(String s){
        char[] chars=s.toCharArray();
        int flag1=0,flag2=0;
        int tem=s.length()+1;//   带商榷
        int flag3=0;
         for (int i=0;i<chars.length;i++){
             if(isIegalChar(chars[i])){
                if(chars[i]=='e'||chars[i]=='E') {
                    flag1+=1;
                    tem=i;
                    if(tem>=2){
                       if(chars[1]!='.'){return false;}
                    }

                }
                if(chars[i]=='.'){
                    flag2+=1;
                    if(i>tem){
                        return false;
                    }
                }
                if(flag1>=2||flag2>=2){
                    return false;
                }


                if(chars[i]=='+'){
                    flag3=i;
                }
                if(chars[i]=='-'){
                    if((i-flag3)==1||(flag3-i)==1){return false;}
                }

             }else {
                 return false;
             }
         }
        return true;
    }
    private static boolean isIegalChar(char a){
        if((a>='0'&&a<='9')||a=='e'||a=='E'||a=='.'||a=='+'||a=='-'){
            return true;
        }
        return false;
    }





    public static boolean solution2(String s){
        if(s==null||s.length()<1){
            return false;
        }

        int index=0;
        if(s.charAt(index)=='+'||s.charAt(index)=='-'){
            index++;
        }
        if(index>=s.length()){
            return false;
        }
        boolean num=true;
        index=scanDigit(s,index);
        if(index<s.length()){
            if(s.charAt(index)=='.'){
                index++;
                index=scanDigit(s,index);
                if(index>=s.length()){
                    num=true;
                }else if(index<s.length()&&(s.charAt(index)=='e'||s.charAt(index)=='E')){
                    num=isExponential(s,index);
                }else {
                    num=false;
                }
            }else if(s.charAt(index)=='e'||s.charAt(index)=='E'){
                num=isExponential(s,index);
            }else {
                num=false;
            }
            return num;
        }
        else {
            return true;
        }

    }


    private static boolean isExponential(String s,int index){

        if(index>=s.length()||s.charAt(index)!='e'||s.charAt(index)!='E'){
            return false;
        }


        index++;

        if(index>s.length()){
            return false;
        }
        if(s.charAt(index)=='+'||s.charAt(index)=='-'){
            index++;
        }

        if(index>=s.length()){
            return false;
        }
        index=scanDigit(s,index);

        return index>=s.length();








    }
    private static  int scanDigit(String s,int index){
        while (index<s.length()&&s.charAt(index)>='0'&&s.charAt(index)<='9')
            index++;
        return index;
    }

    public static void main(String[] args){

        System.out.println("1,真实是 true ,测试是 "+solution1("1e125")+" ,"+solution2("1e125"));
        System.out.println("2,真实是 flase ,测试是 "+solution1("+12e23")+","+solution2("+12e23"));
        System.out.println("3,真实是 true ,测试是 "+solution1("-4.5e23")+","+solution2("-4.5e23"));
        System.out.println("4,真实是 true ,测试是 "+solution1("4.e45")+","+solution2("4.e45"));
        System.out.println("5,真实是 true ,测试是 "+solution1("+4.E1")+","+solution2("+4.E1"));
        System.out.println("6,真实是 flase ,测试是 "+solution1("+4.5E1.2")+","+solution2("+4.5E1.2"));
        System.out.println("7,真实是 true ,测试是 "+solution1("1e125")+","+solution1("1e125"));
        System.out.println("真实是 true ,测试是 "+solution1("1e125"));
        System.out.println("真实是 true ,测试是 "+solution1("1e125"));
        System.out.println("真实是 true ,测试是 "+solution1("1e125"));
        System.out.println("真实是 true ,测试是 "+solution1("1e125"));
        System.out.println("真实是 true ,测试是 "+solution1("1e125"));


    }





}
