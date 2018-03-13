package LinearListClass;

/**
 * Created by windons8 on 2018/3/3.
 */
public class SortedList<T extends Comparable> {
    private class Node{
        public T data;
        public Node next;

        public Node(T data,Node next){
            this.data=data;
            this.next=next;
        }

//        public boolean compareTo(T data){
//            return this.data>data?
//        }
    }

    private Node header;
    private Node tail;
    private int size;
    public SortedList(){
        header=null;
        tail=null;
    }

    public SortedList(T data){
        this.header=new Node(data,null);
        this.tail=this.header;
        size++;
    }

    public Node getNodeByValue(Node node){
       if(this.header!=null) {
           Node temp = this.header;
           do {
               if (temp.data== node.data) {
                   return temp;
               }
               temp = temp.next;
           } while (temp != null);
       }
       return null;
    }


    public void addNode(T data){
        //  默认 从小 到大  的排序
        if(this.header==null){
            new SortedList<>(data);
            this.header.next=new Node(data,null);
            this.tail=this.header.next;
        }else {
            Node node=this.header;
            boolean flap=false;
            do{
                if(node.next!=null){
                    if((node.data.compareTo(data)>0)&&(node.next.data.compareTo(data)<=0)){


                    }
                }else if(node.data.compareTo(data)>0){
                    Node newnode=new Node(data,null);
                    node.next=newnode;
                    this.tail=newnode;
                }else {
                    Node newnode=new Node(data,null);
                    newnode.next=node;
                    this.tail=node;
                }

            }while (node!=null);
        }



    }







}
