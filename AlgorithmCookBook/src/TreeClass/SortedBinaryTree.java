package SortedBinaryTree;

import java.util.*;

/**
 * Created by windons8 on 2017/11/17.
 */
public class SortedBinaryTree <T extends Comparable>{
    static class Node{
        Object data;
        Node parent;
        Node left;
        Node right;

        public Node(Object data,Node parent,Node left,Node right){
            this.data=data;
            this.left=left;
            this.right=right;
            this.parent=parent;
        }
        public String toString(){
            return "[data="+data+"]";
        }
        public boolean equals(Object obj){
            if(this==obj){
                return true;
            }
            if(obj.getClass()==Node.class){
                Node target=(Node)obj;
                return data.equals(target.data)&&left==target.left&&right==target.right&&parent==target.parent;
            }
            return false;
        }
    }
    private Node root;

    public SortedBinaryTree(){
        root=null;
    }
    public SortedBinaryTree(T o){
        root=new Node(o,null,null,null);
    }
    public void add(T ele){
        if(root==null){
            root=new Node(ele,null,null,null);
        }else{
            Node current=root;
            Node parent=null;
            int cmp=0;
            do{
                parent=current;
                cmp=ele.compareTo(current.data);
                if(cmp>0){
                    current=current.right;
                }else{
                    current=current.left;
                }
            }while (current!=null);
            Node newnode=new Node(ele,parent,null,null);

            if(cmp>0){
                parent.right=newnode;
            }else{
                parent.left=newnode;
            }
        }
    }

    public void remove(T ele){
        Node target=getNode(ele);
        if(target==null){
            return;
        }
        if(target.left==null&&target.right==null){
            if(target==root){
                root=null;
            }else{
                if(target==target.parent.left){
                    target.parent.left=null;
                }else{
                    target.parent.right=null;
                }
                target.parent=null;
            }
        }else if(target.left==null&&target.right!=null){
            if(target==root){
                root=target.right;
            }else{
                if(target==target.parent.left){
                    target.parent.left=target.parent;
                }else{
                    target.parent.right=target.parent;
                }
                target.right.parent=target.parent;
            }
        }else if(target.left!=null&&target.right==null){
            if(target==root){
                root=target.left;
            }else{
                if(target==target.parent.left){
                    target.parent.left=target.left;
                }else{
                    target.parent.right=target.left;
                }
                target.left.parent=target.parent;
            }
        }else{
            Node leftmaxNode=target.left;
            while (leftmaxNode.right!=null){
                leftmaxNode=leftmaxNode.right;
            }
            leftmaxNode.parent.right=null;
            leftmaxNode.parent=target.parent;
            if(target==target.parent.left){
                target.parent.left=leftmaxNode;
            }else{
                target.parent.right=leftmaxNode;
            }
            leftmaxNode.left=target.left;
            leftmaxNode.right=target.right;

            target.parent=target.left=target.right=null;
        }
    }

    public Node getNode(T ele) {
        Node p=root;
        while (p!=null){
            int cmp=ele.compareTo(p.data);
            if(cmp<0){
                p=p.left;
            }else if(cmp>0){
                p=p.right;
            }else{
                return p;
            }
        }
        return null;
    }

    public List<Node>  breadthFirst(){
        Queue<Node> queue=new ArrayDeque<Node>();
        List<Node> list=new ArrayList<Node>();
        if(root!=null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            list.add(queue.peek());
            Node p=queue.poll();
            if(p.left!=null){
                queue.offer(p.left);
            }
            if(p.right!=null){
                queue.offer(p.right);
            }
        }
        return list;
    }
}

