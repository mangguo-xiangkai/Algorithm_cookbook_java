/**
 *
 *
 *
 * 对字串符进行截取
 * Created by windons8 on 2018/2/11.
 */
public class SplitString {
    String SplitStr=null;
    int SplitByte;
    public SplitString(String str,int bytes){
        SplitStr=str;
        SplitByte=bytes;
        System.out.println("The String is: '"+SplitStr+"'; SplitBytes="+SplitByte);
    }
    public void  SplitIt(){
        int loopCount;
        loopCount=(SplitStr.length()%SplitByte==0)?(SplitStr.length()/SplitByte):(SplitStr.length()/SplitByte+1);
        System.out.println("will split into "+loopCount);

        for (int i=1;i<=loopCount;i++){
            if(i==loopCount){
                System.out.println(SplitStr.substring((i-1)*SplitByte,SplitStr.length()));
            }else {
                System.out.println(SplitStr.substring((i-1)*SplitByte,(i*SplitByte)));
            }
        }
    }

    public static void main(String[] arg){
        SplitString ss=new SplitString("的空间佛I算法的回复sdfsfsdfa 山东发",5);
        ss.SplitIt();
    }
}
