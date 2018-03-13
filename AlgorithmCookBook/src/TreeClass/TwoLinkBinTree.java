package TreeClass;

import org.omg.CORBA.TRANSACTION_MODE;

/**
 * Created by windons8 on 2018/3/1.
 */


//   此类 主要用于 快速 构建 二叉树
public class TwoLinkBinTree<E> {

    public class TreeNode{
        public E data;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(){
        }
        public TreeNode(E data){
            this.data=data;
        }
        public TreeNode(E data,TreeNode left,TreeNode right){
            this.data=data;
            this.left=left;
            this.right=right;
        }

        public TreeNode TreeNodeAddNode(TreeNode left,TreeNode right){
            this.left=left;
            this.right=right;
            return  this;
        }
    }

    private TreeNode root;

    public TwoLinkBinTree(){
        this.root=new TreeNode();
    }

    public TwoLinkBinTree(E data){
        this.root=new TreeNode(data);
    }

    public TreeNode addleftNode(TreeNode parent,E leftData) {
        if (parent == null) {
            throw new RuntimeException(parent + "此节点为空，不能添加子节点");
        }
        if(parent.left!=null){
            throw new RuntimeException(parent+"的左子节点不为空,异常");
        }else {
            TreeNode newnode=new TreeNode(leftData);
            parent.left=newnode;
            return newnode;
        }
    }

    public TreeNode addRightNode(TreeNode parent,E rightData) {
        if (parent == null) {
            throw new RuntimeException(parent + "此节点为空，不能添加子节点");
        }
        if(parent.right!=null){
            throw new RuntimeException(parent+"的右子节点不为空,异常");
        }else {
            TreeNode newnode=new TreeNode(rightData);
            parent.right=newnode;
            return newnode;
        }
    }


    public boolean isEmpty(){
        return root.data==null;
    }

    public TreeNode getRoot(){
        if(isEmpty()){
            throw new RuntimeException("树为空");
        }
        return root;
    }


    public  TreeNode getParent(){
        return null;
    };



    public E getLeftChild(TreeNode parent){
        if(parent==null){
            throw new RuntimeException(parent +"为空");
        }
        return parent.left==null?null:(E)parent.left.data;
    }

    public E getRightChild(TreeNode parent){
        if(parent==null){
            throw new RuntimeException(parent +"为空");
        }
        return parent.right==null?null:(E)parent.right.data;
    }

    public int getDeep(){
        return deep(root);
    }
    private int deep(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.left==null&&node.right==null){
            return 1;
        }else {
            int leftDeep=deep(node.left);
            int rightDeep=deep(node.right);

            int max=leftDeep>rightDeep?leftDeep:rightDeep;
            return max+1;
        }

    }
}
