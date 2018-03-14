package StackQueneClass;

/**
 * Created by windons8 on 2017/11/15.
 */
public class LinkStack <T>{
    private class Node{
        private T data;
        private Node next;
        public Node(){
        }
        public Node(T data,Node next){
            this.data=data;
            this.next=next;
        }
    }
    private Node top;
    private int size;
    public LinkStack(){
        top=null;
    }
    public LinkStack(T element){
        top=new Node(element,null);
        size++;
    }
    public int length(){
        return size;
    }
    public void push(T element){
        top=new Node(element,top);
        size++;
    }
    public T top(){
        Node oldtop=top;
        top=top.next;
        oldtop.next=null;
        size--;
        return oldtop.data;
    }
    public T peek(){
        return top.data;
    }
    public boolean empty(){
        return size==0;
    }
    public void clear(){
        top=null;
        size=0;
    }
    public String toString(){
        if(empty()){
            return "[]";
        }else{
            StringBuilder sb=new StringBuilder("[");
            for (Node current=top;current!=null;current=current.next){
                sb.append(current.data.toString()+",").toString();
            }
            return  sb.append("]").toString();
        }
    }




}
