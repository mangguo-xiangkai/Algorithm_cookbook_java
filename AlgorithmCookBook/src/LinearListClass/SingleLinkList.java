package LinearListClass;



/**
 * Created by windons8 on 2017/11/14.
 */
public class SingleLinkList <T> extends ListClass{

    // 作 引用  用  唯一的属性data,
    public class Node{
        private T data;
        public Node next;
        public Node(){
        }
        public Node(T data,Node next){
            this.data=data;
            this.next=next;
        }
        public boolean hasNext(){
            return this.next==null;
        }




    }
    private Node header;
    private Node tail;
    private  int size;
    public SingleLinkList(){
        header=null;
        tail=null;
    }
    public SingleLinkList(T element){

//        header=new Node(element,tail);
        size++;
    }
    public int length(){
        return size;
    }

    public int getSize() {
        return size;
    }
    @SuppressWarnings("unchecked")
    public Node getNodeByIndex(int index){
        if (index<0||index>size-1){
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node current=header;
        for(int i=0;i<size&&current!=null;i++,current=current.next){
            if(i==index){
                return current;
            }
        }
        return null;
    }
    public int locate(T element){
        Node current=header;
        for(int i=0;i<size&&current!=null;i++,current=current.next){
            if (current.data==element){
                return i;
            }
        }
        return -1;
    }
    public Node getHeader(){
        return this.header;
    }

//    public boolean hasNext(){
//        return ;
//    }
    public void insert(T element,int index){
        if(index<0||index>size)
            throw new IndexOutOfBoundsException("索引越界");
        if(header==null){
            add(element);
        }else {
            if(index==0){
                addAtHeader(element);
            }else{

                Node newnode=new Node(element,getNodeByIndex(index));
                getNodeByIndex(index-1).next=newnode;
//                Node prev=getNodeByIndex(index-1);
//                prev.next=new Node(element,prev.next);
                size++;
            }
        }
    }
    public void add(T element){
        if(header==null){
            header=new Node(element,null);
            tail=header;
        }else{
            Node newnNode=new Node(element,null);
            tail.next=newnNode;
            tail=newnNode;
        }
        size++;
    }
    public void addAtHeader(T element){
        header=new Node(element,header);
        if (tail==null){
            tail=header;
        }
        size++;
    }
    public T delete(int index){
        if (index<0||index>size-1)
            throw new IndexOutOfBoundsException("索引越界");
        Node del=null;
        if(index==0){
            del=header;
            header=header.next;
        }else{
            Node prev=getNodeByIndex(index-1);
            del =prev.next;
            prev.next=del.next;
            del.next=null;
            del.data=null;
        }
        size--;
        return del.data;
    }
    public T remove(){
        return delete(size-1);
    }
    public boolean empty(){return size==0;}
    public void clear(){
        header=null;
        tail=null;
        size=0;
    }
    public String toString(){
        if(empty()){
            return "[]";
        }else {
            StringBuilder sb=new StringBuilder("[");
            for (int i=0;i<size;i++){
                sb.append(getNodeByIndex(i).data.toString()+",");
            }


//            for (Node current=header;current!=null;current=current.next){
//                sb.append(current.data.toString()+",");
//            }
//            int len=sb.length();
//            return sb.delete(len-2,len).append("]")+",".toString();
            return sb.append("]").toString();
        }
    }




}
