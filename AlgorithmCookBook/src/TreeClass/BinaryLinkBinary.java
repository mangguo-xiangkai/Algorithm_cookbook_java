package  TreeClass;

import TreeClass.TreeParent;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by windons8 on 2017/11/16.
 */
public class BinaryLinkBinary<T> {
    public  class Node{
        public T data;
        public Node left;
        public Node right;
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
        public void  Nodeleft(T data,Node left){
            this.data=data;
            this.left=left;
        }
        public void Noderight(T data, Node right){
            this.data=data;
            this.right=right;
        }
    }
    private BinaryLinkBinary.Node[] nodes;
    private int DEFAULT_SIZE=100;
    private int nodessize;

    public BinaryLinkBinary(){
        this.nodessize=DEFAULT_SIZE;
        nodes=new BinaryLinkBinary.Node[nodessize];
    }
    public BinaryLinkBinary(T data){
        this();
        nodes[0] =new Node(data);
        nodessize++;
    }

    public void addleft(T data,Node parent){
        if(parent.left==null){
            Node newnode=new Node(data);
            parent.left=newnode;
        }else{
            throw new RuntimeException("error");
        }
    }
    public void addright(T data,Node parent){
        if(parent.right==null){
            Node newnode=new Node(data);
            parent.right=newnode;
        }else
            throw new RuntimeException("error");
    }
    public boolean isempty(){
        return nodessize==0;
    }

    public int deep(Node node){
        if(node==null)
            return 0;
        else if(node.right==null&&node.left==null)
            return 1;
        else {
            int leftdeep=deep(node.left);
            int rightdeep=deep(node.right);
            int max=leftdeep>rightdeep?leftdeep:rightdeep;
            return max+1;
        }
    }





}
