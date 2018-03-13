package TreeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windons8 on 2017/11/16.
 */
public class TreeParent<E> {
    public static class Node<T>{
        private T data;
        private int parent;
        public Node(){
        }
        public Node(T data){
            this.data=data;
        }
        public Node(T data,int parent){
            this.data=data;
            this.parent=parent;
        }
        public String toString(){
            return "TreeParent$Node[data="+data+",parent="+parent+"]";
        }
    }
    private final int DEFAULT_TREE_SIZE=100;
    private int treeSize=0;
    private int nodesNums;
    private TreeParent.Node<E>[] nodes;
    @SuppressWarnings("unchecked")
    public TreeParent(E data){
        treeSize=DEFAULT_TREE_SIZE;
        nodes=new TreeParent.Node[treeSize];
        nodes[0]=new TreeParent.Node<E>(data,-1);
        nodesNums++;
    }
    public TreeParent(E data,int treeSize){
        this.treeSize=treeSize;
        nodes=new TreeParent.Node[treeSize];
        nodes[0]=new TreeParent.Node<E>(data,-1);
        nodesNums++;
    }
    public void addNode(E data, TreeParent.Node parent){
        for (int i=0;i<treeSize;i++){
            if(nodes[i]==null){
                nodes[i]=new TreeParent.Node<E>(data,pos(parent));
                nodesNums++;
                return;
            }
        }
        throw new RuntimeException("");
    }
    public int pos(TreeParent.Node node){
        for (int i=0;i<treeSize;i++){
            if(nodes[i]==node){
                return i;
            }
        }
        return -1;
    }
    public boolean isempty(){
        return nodes[0]==null;
    }
    public TreeParent.Node<E> root(){
        return nodes[0];
    }
    public TreeParent.Node<E> paraent(TreeParent.Node node){
        return nodes[node.parent];
    }
    public List<TreeParent.Node<E>> children(TreeParent.Node parent){
        List<TreeParent.Node<E>> list=new ArrayList<TreeParent.Node<E>>();
        for (int i=0;i<treeSize;i++){
            if(nodes[i]!=null&&nodes[i].parent==pos(parent)){
                list.add(nodes[i]);
            }
        }
        return list;
    }
    public int deep(){
        int max=0;
        for (int i=0;i<treeSize&&nodes[i]==null;i++){
            int def=1;
            int m=nodes[i].parent;
            while (m!=-1&&nodes[m]!=null){
                m=nodes[m].parent;
                def++;
            }
            if(max<def){
                max=def;
            }
        }
        return max;
    }

}
