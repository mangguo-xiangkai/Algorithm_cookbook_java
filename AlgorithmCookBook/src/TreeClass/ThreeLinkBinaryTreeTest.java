package TreeClass;

import javax.swing.table.TableRowSorter;

/**
 * Created by windons8 on 2017/11/16.
 */
public class ThreeLinkBinaryTreeTest {
    public static void main(String[] srg){
        ThreeLinkBinaryTree<String> tlbt=new ThreeLinkBinaryTree<String>("root");
        ThreeLinkBinaryTree.Node root=tlbt.nodes(0);
        tlbt.addleft("safd1",root);
        tlbt.addright("dddd2",root);
        tlbt.addright("kk3",tlbt.nodes(2));
        tlbt.addright("dsf4",tlbt.nodes(3));
        tlbt.addleft("afds5",tlbt.nodes(4));
        System.out.println(tlbt.nodes(1));

        System.out.println(tlbt.parent(tlbt.nodes(3)));
        System.out.println(tlbt.deep());
    }
}
