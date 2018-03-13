package TreeClass;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by windons8 on 2017/11/16.
 */
public class TreeChild<E> {
    private static class SonNode{
        private SonNode next;
        private int pos;
        public SonNode(){
        }
        public SonNode(int pos,SonNode next){
            this.next=next;
            this.pos=pos;
        }
    }
    public static class Node<T>{
        private T data;
        SonNode first;
//        public int pos;
        public Node(T data){
            this.data=data;
            this.first=null;
        }
        public String toString(){

            StringBuilder sb=new StringBuilder("Tree[");
            if(first!=null){

                return "TreeChild$Node [data="+data+",child-pos=" +first.pos+"]";
            }else{
                return "Tree$Node [data="+data+",child-pos=-1]";
            }
        }
    }
    private final int DEFAULT_TREE_SIZE=100;
    private int treesize=0;
    private Node<E>[] nodes;
    private int nodesnum=0;

//    @SuppressWarnings("unchecked");
    public TreeChild(E data){
        treesize=DEFAULT_TREE_SIZE;
        nodes=new Node[treesize];
        nodes[0]=new Node<E>(data);//  这一步该 成   nodes[0].data=data   行不行
        nodesnum++;
    }
    public TreeChild(E data,int treesize){
        this.treesize=treesize;
        nodes=new Node[treesize];
        nodes[0]=new Node<E>(data);
//        nodes[0].data=data;
        nodesnum++;
    }
    public void addNode(E data,Node parent) {
        ensurecapatity();
        nodes[nodesnum]=new Node<E>(data);
                if(parent.first==null){
                    parent.first = new SonNode(nodesnum, null);
                }else {
                    SonNode next = parent.first;
                    while (next.next != null) {
                        next = next.next;
                    }
                    next.next = new SonNode(nodesnum, null);
                }
        nodesnum++;
    }
    public Node<E> child(Node parent,int index){
        SonNode current;
        int i=1;
        for(current=parent.first;current!=null;current=current.next){
            if(i==index)
                return nodes(current.pos);
            i++;
        }
        return null;
    }
    public int deep(){
        return Nodedeep(nodes(0));
    }
    public int size(){
        return nodesnum;
    }
    public int Nodedeep(Node node){
        if(node.first==null){
            return 1;
        }else {
            int max=0;
            SonNode next=node.first;
            while (next!=null){
                int tem=Nodedeep(nodes(next.pos));
                if(tem>max)
                    max=tem;
                next=next.next;
            }
            return max+1;
        }
    }

    public List<Node<E>> childrenlist(Node parent){
        List<Node<E>> list=new ArrayList<Node<E>>();
        SonNode next;
        for (next=parent.first;next!=null;next=next.next){
            list.add(nodes[next.pos]);
        }
        return list;
    }


    private int pos(Node node){
        for (int i=0;i<nodesnum;i++){
            if(nodes[i]==node)
                return i;
        }
        return -1;
    }
    public boolean isempty(){
        return nodesnum==0;
//        return nodes(0)==null;
    }
    public Node<E>  nodes(int i){
        return nodes[i];
    }
    private void ensurecapatity(){
        if(nodesnum==treesize)
            treesize=(int)(treesize*1.5);
    }
//    public String toSting(){
//        if(isempty()){
//            return "[]";
//        }else{
//            StringBuilder sb=new StringBuilder("[");
//            if()
//        }
//
//
//    }
}
