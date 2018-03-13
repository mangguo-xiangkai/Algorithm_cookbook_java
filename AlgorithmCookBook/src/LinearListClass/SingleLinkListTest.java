package LinearListClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by windons8 on 2017/11/14.
 */

//class Node<T>{
//    public T data;
//    public Node next;
//    public Node(){
//    }
//    public Node(T data,Node next){
//        this.data=data;
//        this.next=next;
//    }
//}

public class SingleLinkListTest {
    public static void main(String[] ags) {


        SingleLinkList<String> slls=new SingleLinkList<String>();
        slls.addAtHeader("sf");
        slls.add("2");
        slls.add("3");
        slls.add("4");
        slls.add("5");
        System.out.println(slls+":::"+slls.length());
        slls.insert("s",2);

        System.out.println(slls);
    }
}
