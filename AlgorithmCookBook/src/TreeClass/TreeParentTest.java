package TreeClass;

import java.util.List;

/**
 * Created by windons8 on 2017/11/16.
 */
public class TreeParentTest {
    public static void main(String[] arg){
        TreeParent<String> tp=new TreeParent<String>("root");
        TreeParent.Node root=tp.root();

        tp.addNode("safd",root);
        tp.addNode("sfa",root);

        List<TreeParent.Node<String>> nodes=tp.children(root);


        System.out.println(nodes.get(1));


    }
}

