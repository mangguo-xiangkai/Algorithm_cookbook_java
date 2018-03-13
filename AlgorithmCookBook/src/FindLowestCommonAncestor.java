import TreeClass.TraversalTree;
import org.omg.CORBA.TRANSACTION_MODE;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by windons8 on 2018/3/7.
 */
public class FindLowestCommonAncestor {

    private static class TreeNode{
        int val;
        List<TreeNode> children=new LinkedList<>();
        public TreeNode(){
        }
        public TreeNode(int val){
            this.val=val;
        }
        public String toString(){
            return val+"";
        }
    }

    public static void  getNodePath(TreeNode root, TreeNode target, List<TreeNode> path){

        if(root==null){
            return;
        }

        path.add(root);

        List<TreeNode> children=root.children;

        for(TreeNode node:children){
            if(node==target){
                path.add(node);
                return;
            }else {
                getNodePath(node,target,path);
            }
        }
        path.remove(path.size()-1);
    }

    public static TreeNode getLastCommonNode(List<TreeNode> p1,List<TreeNode> p2){

        Iterator<TreeNode> it1=p1.iterator();
        Iterator<TreeNode> it2=p2.iterator();

        TreeNode last=null;

        while (it1.hasNext()&&it2.hasNext()){
            TreeNode tmp=it1.next();
            if(tmp==it1.next()){
                last=tmp;
            }
        }
        return last;
    }

    public static TreeNode getLastCommonParent(TreeNode root,TreeNode p1,TreeNode p2){
        if(root==null||p1==null||p2==null){
            return null;
        }
        List<TreeNode> path1=new LinkedList<>();
        getNodePath(root,p1,path1);
        List<TreeNode> path2=new LinkedList<>();
        getNodePath(root,p2,path2);
        return getLastCommonNode(path1,path2);
    }



    public void test01(){
        TreeNode n1=new TreeNode(1);
        TreeNode n2=new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);
        TreeNode n6=new TreeNode(6);
        TreeNode n7=new TreeNode(7);
        TreeNode n8=new TreeNode(8);

        n1.children.add(n2);
        n1.children.add(n3);

        n2.children.add(n4);
        n4.children.add(n6);
        n4.children.add(n7);

        n3.children.add(n5);

        n5.children.add(n8);
        System.out.println(getLastCommonParent(n1,n6,n7));
    }









    public static void main(String[] args){
            new FindLowestCommonAncestor().test01();
    }


}
