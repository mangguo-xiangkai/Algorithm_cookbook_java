import java.util.*;

/**
 * Created by windons8 on 2018/2/27.
 */

import TreeClass.TraversalTree;
import TreeClass.TwoLinkBinTree;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class zhiXingPrintBinaryTree<T> {

    public ArrayList<ArrayList<T>> Solution1(TwoLinkBinTree<T>.TreeNode pRoot){

        int level=1;
        Stack<TwoLinkBinTree<T>.TreeNode> s1=new Stack<>();
        s1.push(pRoot);
        Stack<TwoLinkBinTree<T>.TreeNode> s2=new Stack<>();

        ArrayList<ArrayList<T>> list=new ArrayList<>();

        while (!s1.empty()||!s2.empty()){
            if (level%2!=0){
                ArrayList<T> temp=new ArrayList<>();
                while (!s1.empty()){
                    TwoLinkBinTree<T>.TreeNode node=s1.pop();
                    if (node!=null){
                        temp.add(node.data);
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }
                if(!temp.isEmpty()){
                    list.add(temp);
                    level++;
                }
            }else {
                ArrayList<T> temp=new ArrayList<>();
                while (!s2.empty()){
                    TwoLinkBinTree<T>.TreeNode node=s2.pop();
                    if(node!=null){
                        temp.add(node.data);
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }
                if(!temp.isEmpty()){
                    list.add(temp);
                    level++;
                }
            }
        }
        return list;
    }



    public ArrayList<ArrayList<T>> Solution2(TwoLinkBinTree<T>.TreeNode pRoot){
        ArrayList<ArrayList<T>> layers=new ArrayList<>();
        if(pRoot==null){
            return layers;
        }
        Deque<TwoLinkBinTree<T>.TreeNode> queue=new LinkedList<>() ;

        queue.offer(pRoot);

        int depth=0;
        while (!queue.isEmpty()){
            depth++;
            ArrayList<T> layer =new ArrayList<>();
            int cur=0, size=queue.size();
            if((depth%2)==0){
                Iterator<TwoLinkBinTree<T>.TreeNode> it= queue.descendingIterator();
                while (it.hasNext()){
                    layer.add(it.next().data);
                }
            }else {
                Iterator<TwoLinkBinTree<T>.TreeNode> it=queue.iterator();
                while (it.hasNext()){
                    layer.add(it.next().data);
                }
            }
            while (cur<size){
                TwoLinkBinTree<T>.TreeNode node=queue.poll();
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                cur++;
            }

            layers.add(layer);
        }
       return layers;
    }



    public static void main(String[] args){
        TwoLinkBinTree<String> binTree=new TwoLinkBinTree<>("跟节点-1");

        TwoLinkBinTree<String>.TreeNode tn21=binTree.addleftNode(binTree.getRoot(),"21");
        TwoLinkBinTree<String>.TreeNode tn22=binTree.addRightNode(binTree.getRoot(),"22");
        TwoLinkBinTree<String>.TreeNode tn31=binTree.addleftNode(tn21,"31");
        TwoLinkBinTree<String>.TreeNode tn32=binTree.addRightNode(tn21,"32");
        TwoLinkBinTree<String>.TreeNode tn33=binTree.addleftNode(tn22,"33");
        TwoLinkBinTree<String>.TreeNode tn34=binTree.addRightNode(tn22,"34");
        System.out.println(binTree.getDeep());

        zhiXingPrintBinaryTree<String> test1=new zhiXingPrintBinaryTree<>();
        ArrayList cd=test1.Solution1(binTree.getRoot());

        cd.forEach(x->System.out.println(x));

        System.out.println("---=====-------------");
        ArrayList ce=test1.Solution2(binTree.getRoot());

        ce.forEach(x->System.out.println(x));


        TraversalTree<String> travlTree=new TraversalTree<>();

        List<TwoLinkBinTree<String>.TreeNode> cc=travlTree.inIterator(binTree.getRoot());
        cc.forEach(x->System.out.println(x.data));


    }
}
