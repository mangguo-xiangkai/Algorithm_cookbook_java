package TreeClass;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windons8 on 2017/11/17.
 */
public class TraversalTree <T>{

    public List<TwoLinkBinTree<T>.TreeNode> preIterator(TwoLinkBinTree<T>.TreeNode node){
        List<TwoLinkBinTree<T>.TreeNode> list=new ArrayList<TwoLinkBinTree<T>.TreeNode>();
        list.add(node);
        if(node.left!=null){
            list.addAll(preIterator(node.left));
        }
        if(node.right!=null){
            list.addAll(preIterator(node.right));
        }
        return list;
    }

    public List<TwoLinkBinTree<T>.TreeNode> inIterator(TwoLinkBinTree<T>.TreeNode node){
        List<TwoLinkBinTree<T>.TreeNode> list=new ArrayList<TwoLinkBinTree<T>.TreeNode>();
        if(node.left!=null){
            list.addAll(inIterator(node.left));
        }
        list.add(node);
        if(node.right!=null){
            list.addAll(inIterator(node.right));
        }
        return list;
    }

    public List<TwoLinkBinTree<T>.TreeNode> postInterator(TwoLinkBinTree<T>.TreeNode node){
        List<TwoLinkBinTree<T>.TreeNode> list=new ArrayList<TwoLinkBinTree<T>.TreeNode>();

        if(node.left!=null){
            list.addAll(preIterator(node.left));
        }
        if(node.right!=null){
            list.addAll(preIterator(node.right));
        }
        list.add(node);

        return list;
    }



}


