package TreeClass;

import sun.util.resources.cldr.ro.TimeZoneNames_ro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by windons8 on 2017/11/16.
 */
public class TreeChildTest {
    public static void main(String[] arg){
        TreeChild<String> tc=new TreeChild<String>("root");
//        TreeChild.Node root=tc.nodes(0);
        tc.addNode("df1",tc.nodes(0));

//        tc.addNode("dsfa",tc.nodes(1));
////        System.out.println(tc.isempty());
        tc.addNode("df2",tc.nodes(0));
        tc.addNode("df3",tc.nodes(0));
        System.out.println(tc.nodes(1));
//        List list=new ArrayList();
//        tc.addNode("fsad",tc.childrenlist(tc.nodes(0)).get(1));
        System.out.println(tc.size());

        System.out.println(tc.childrenlist(tc.nodes(0)));
        System.out.println(tc.child(tc.nodes(0),2));

    }
}
