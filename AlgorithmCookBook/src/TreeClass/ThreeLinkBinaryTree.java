package TreeClass;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.concurrent.TransferQueue;

/**
 * Created by windons8 on 2017/11/16.
 */
public class ThreeLinkBinaryTree <T>{
    public class Node{
        private T data;
        private Node parent;
        private Node left;
        private Node right;

        public Node(){
        }
        public Node(T data){
            this.data=data;
        }
        public Node(T data,Node left,Node right){
            this.data=data;
            this.left=left;
            this.right=right;
        }

        public String toString(){
            StringBuilder sb =new StringBuilder("[");
            sb.append(this.data+",]").toString();
            return sb.toString();
        }
    }
    private ThreeLinkBinaryTree.Node[] nodes;
    private int DEFAULT_SIZE=50;
    private int nodessize=0;
//    private

    public ThreeLinkBinaryTree(){
//        this.nodessize=DEFAULT_SIZE;
        nodes=new ThreeLinkBinaryTree.Node[DEFAULT_SIZE];
    }
    public ThreeLinkBinaryTree(T data){
        this();
        nodes[0]=new Node(data);
        nodessize++;
    }

    public void addleft(T data,Node parent){
        if(parent!=null&&parent.left==null){
            Node newnode=new Node(data);
            nodes[nodessize++]=new Node(data);
            newnode.parent=parent;
            parent.left=newnode;
        }else{
            throw new RuntimeException("error");
        }
    }
    public void addright(T data,Node parent){
        if(parent!=null&&parent.right==null){
            Node newnode=new Node(data);
            nodes[nodessize++]=newnode;
            newnode.parent=parent;
            parent.right=newnode;
        }else{
            throw new RuntimeException("error");
        }
    }




    public boolean isempty(){
        return nodes[0]==null;
    }
    public T parent(Node node){
        if(node.parent!=null)
            return node.parent.data;
        else
            return null;
    }
    public T leftchild(Node node){
        if(node.left!=null)
            return node.left.data;
        else
            return null;
    }
    public T rightchild(Node node){
        if(node.right!=null)
            return node.right.data;
        else
            return null;
    }
    public int deep(){
        return nodedeep(nodes[0]);
    }
    public int nodedeep(Node node){
        if(node==null){
            return 0;
        }else if(node.left==null&&node.right==null){
            return 1;
        }else{
            int leftdeep=nodedeep(node.left);
            int rightdeep=nodedeep(node.right);
            int max=leftdeep>rightdeep?leftdeep:rightdeep;
            return max+1;
        }
    }
    public Node nodes(int index){
        return nodes[index];
    }


}
