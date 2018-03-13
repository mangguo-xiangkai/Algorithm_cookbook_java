package StackQueneClass;

/**
 * Created by windons8 on 2017/11/15.
 */
public class LinkQueneTest {
    public static void main(String[] ags){
        LinkQuene<String> lqs=new LinkQuene<String>();
        LinkQuene.Node df=lqs.new Node();  //  怎么这么怪异
        df.next=lqs.new Node();

        lqs.add("sf");
        lqs.add("df");
        lqs.add("25");
        lqs.remove();
        System.out.println(lqs);
    }
}
