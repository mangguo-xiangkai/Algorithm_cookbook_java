package LinearListClass;

import java.util.LinkedList;

/**
 * Created by windons8 on 2017/11/14.
 */
public class DoubleLinkListTest {
    public static void main(String[] arg){
        DoubleLinkList<String> dlls=new DoubleLinkList<String>();
        dlls.add("afdasf");
        dlls.add("afdsf");
        dlls.add("lkkk");
        dlls.insert("safd",1);
        dlls.delete(3);

        System.out.println(dlls.locate("safd"));
        LinkedList de=new LinkedList();
        de.add(54);
        de.push("fas");
//        de.
        System.out.println(de);
    }
}
