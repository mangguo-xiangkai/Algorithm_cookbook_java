package LinearListClass;

/**
 * Created by windons8 on 2017/11/14.
 */
public class DoubleLinkList <T>{
    private class Node{
        private T data;
        private Node pre;
        private Node next;
        public Node(){
        }
        public Node(T data,Node pre,Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
    }
//    private T element;
    private Node header;
    private Node tail;
    private int size;
    public DoubleLinkList(){
        header=null;
        tail=null;
    }
    public DoubleLinkList(T element){
        header=new Node(element,null,null);
        tail=header;
        size++;
    }
    public void add(T element){
        if (size==0){
            header=new Node(element,null,null);
            tail=header;
        }else {
            Node newNode= new Node(element, tail, null);
            tail.next =newNode;
            tail = newNode;
        }
        size++;
    }
    private Node getNodeByIndex(int index){
        if (index<0||index>size-1)
            throw new IndexOutOfBoundsException("索引越界");
        else{
            Node current=header;
            for (int i=0;i<size&&current!=null;i++,current=current.next){
                if (index==i)
                    return current;
            }
        }
        return null;
    }
    public int length(){
        return size;
    }
    public void addAtHeader(T element){
        if (size==0){
            Node newnode=new Node(element,null,null);
            header=newnode;
            tail=newnode;
        }else{
            Node newNode=new Node(element,null,header);
            header.pre=newNode;
            newNode=header;
        }
        size++;
    }
    public void delete(int index){
        if(size==0){
        }else{
            if(index<0||index>size-1)
                throw new IndexOutOfBoundsException("索引越界");
            else if(index==0){
                header=getNodeByIndex(index+1);
                header.pre=null;
            }else if(index==size-1){
                tail=getNodeByIndex(size-2);
                tail.next=null;
            }else{
                Node prev=getNodeByIndex(index-1);
                prev.next=getNodeByIndex(index).next;
                prev.next.pre.pre=null;//  我认为很重要
                prev.next.pre=prev;
            }

            size--;
        }
    }
    public void clear(){
        header=null;
        tail=null;
        size=0;
    }
    public void insert(T element,int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("");
        }else{
            Node newnode=new Node(element,getNodeByIndex(index-1),getNodeByIndex((index)));//  建立新链接，并没有改变原有链接
            getNodeByIndex(index).pre=newnode;
            getNodeByIndex(index-1).next=newnode;
        }
        size++;
    }
    public int locate(T element){
        for (int i=0;i<size-1;i++) {
            if (getNodeByIndex(i).data == element) {
                return i;
            }
        }
        return -1;
    }
    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        for (int i=0;i<size;i++){

                sb.append(getNodeByIndex(i).data.toString() + ",");

        }
        return sb.append("]").toString();
    }



}
