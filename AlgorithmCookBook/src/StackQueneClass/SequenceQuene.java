package StackQueneClass;

import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/15.
 */
public class SequenceQuene <T>{
    private int DEFAULT_SIZE=16;
    private int capatity;
    private Object[] elementData;
    private int front=0;
    private int rear=0;
//    private int size;
    public SequenceQuene(){
        capatity=DEFAULT_SIZE;
        elementData=new Object[capatity];
    }
    public SequenceQuene(T element){
        this();
        elementData[0]=element;
        rear++;
    }
    public SequenceQuene(T element,int initsize){
        this.capatity=initsize;
        elementData=new Object[capatity];
        elementData[0]=element;
        rear++;
    }
    public int length(){
        return rear-front;
    }
    public void add(T element){
        if(rear>capatity-1){
            throw new  IndexOutOfBoundsException("索引越界");
        }
        elementData[rear++]=element;
    }

    @SuppressWarnings("unchecked")
    public T remove(){
        if(empty()){
            throw new IndexOutOfBoundsException("");
        }
        T oldvalue=(T)elementData[front];
        elementData[front++]=null;
        return oldvalue;
    }

    public boolean empty(){
        return rear==front;
    }
    public void clear(){
        Arrays.fill(elementData,null);
        front=0;
        rear=1;
    }
    public String toString(){
        if(empty()){
            return "[]";
        }else {
            StringBuilder sb=new StringBuilder("[");
            for (int i=front;i<rear;i++){
                sb.append(elementData[i]+",").toString();
            }
            return sb.append("]").toString();
        }

    }
}
