package StackQueneClass;

/**
 * Created by windons8 on 2017/11/14.
 */
public class SequenceStackTest {
    public static void main(String[] arg){
        SequenceStack<String> ss=new SequenceStack<String>();
        ss.push("safddf");
//        ss.push("fas","afsd");
        System.out.println(ss);
        System.out.println(ss.pop());

    }
}
