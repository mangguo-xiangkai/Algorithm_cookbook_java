package StackQueneClass;

import java.util.logging.LoggingPermission;

/**
 * Created by windons8 on 2017/11/15.
 */
public class LoopQuene<T> {
    private int DEFAULT_SIZE=10;
    private int capatity;
    private Object[] elementData;
    private int front=0;
    private int rear=0;
    public LoopQuene(){
    }
    public LoopQuene(T element){
        this();
        elementData[0]=element;
        rear++;
    }
    public LoopQuene(T element,int intisize){
        this.capatity=intisize;
        elementData=new Object[capatity];
        elementData[0]=element;
        rear++;
    }
    public int length(){
        if(empty()){
            return 0;
        }
        return rear>front?rear-front:capatity-(rear-front);
    }
    public boolean empty(){
        return rear==front&&elementData[front]==null;
    }
    public void add(T element){
        if(rear==front&&elementData[front]==null) {
///    throw
//            encapatity();
        }
        elementData[rear++]=element;
        rear=rear==capatity?0:rear;
    }
    public T remove(){
        if(empty()){
            throw new IndexOutOfBoundsException("");
        }
        T oldvalue=(T)elementData[front];
        elementData[front++]=null;

        front=front==capatity?0:front;
        return oldvalue;
    }
    public String toString(){
        if(empty()){
            return "[]";
        }else{
            if(front<rear){
                StringBuilder sb=new StringBuilder("[");
                for(int i=front;i<rear;i++){
                    sb.append(elementData[i].toString()+",");
                }
                return sb.append("]").toString();
            }else {
                StringBuilder sb=new StringBuilder("[");
                for (int i=front;i<capatity;i++){
                    sb.append(elementData[i].toString()+",").toString();
                }
                for (int i=0; i<rear;i++){
                    sb.append(elementData[i].toString()+",");
                }
                return sb.append("]").toString();
            }
        }
    }



}
