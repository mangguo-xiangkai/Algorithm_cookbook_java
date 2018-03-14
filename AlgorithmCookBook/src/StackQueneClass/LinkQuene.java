package StackQueneClass;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.omg.CORBA.NO_IMPLEMENT;

/**
 * Created by windons8 on 2017/11/15.
 */
public class LinkQuene <T>{
    public class Node{
        private T data;
        public Node next;
        public Node(){
        }
        public Node(T data,Node next){
            this.data=data;
            this.next=next;
        }
    }
    private Node front;
    private Node rear;
    private int size=0;
    public LinkQuene(){

    }
    public LinkQuene(T element){
        front=new Node(element,rear);
        rear=front;
        size++;
    }
    public void add(T element){
        if(size==0){
            front=new Node(element,rear);
            rear=front;
            size++;
        }else {
            Node newnode=new Node(element,null);
            rear.next=newnode;
            rear=newnode;
            size++;
        }
    }
    public void  remove(){
        if(size==0){
            throw new IndexOutOfBoundsException("一空");
        }else{
            Node tt=front;
//            front=null;
            front=front.next;
            tt.next=null;
            size--;
        }
    }
    public boolean isempty(){
        return size==0;
    }
    public String toString(){
        if(isempty()){
            return "[]";
        }else{
            StringBuilder sb=new StringBuilder("[");
            for(Node i=front;i!=null;i=i.next){
                sb.append(i.data.toString()+",").toString();
            }
            return  sb.append("]").toString();
        }
    }



}
