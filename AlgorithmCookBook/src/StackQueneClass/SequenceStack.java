package StackQueneClass;

import java.sql.SQLClientInfoException;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/14.
 */
public class SequenceStack <T>{
    private int size;
    private T element;
    private int capacity;
    private int capacitycrement;
    private Object[] elementData;
    private int DEFAULY_SIZE=10;
    public SequenceStack() {
        capacity=DEFAULY_SIZE;
        elementData=new Object[capacity];
    }
    public SequenceStack(T element){
        this();
        elementData[0]=element;
        size++;
    }
    public SequenceStack(T element,int initsize){
        this.capacity=initsize;
        elementData=new Object[capacity];
        elementData[0]=element;
        size++;
    }
    public SequenceStack(T element,int initsize,int capacitycrement){
        this.capacity=initsize;
        elementData=new Object[capacity];
        this.capacitycrement=capacitycrement;
        elementData[0]=element;
        size++;
    }
    public  int length(){
        return size;
    }
    private void ensureCapacity(int mincapacity){
        if(mincapacity>capacity){
            while (capacity<mincapacity){
                capacity+=capacitycrement;
            }
        }else{
            while (capacity<mincapacity){
                capacity<<=1;
            }
        }
        elementData= Arrays.copyOf(elementData,capacity);
    }

    public void push(T element){
        elementData[size]=element;
        size++;
    }
    public T pop(){
        T oldValue=(T)elementData[size-1];
        Object oldvalue=elementData[size-1];
        elementData[--size]=null;
        return oldValue;

    }
    public T peek(){
//        Object peek()
//        return elementData[size-1]
        return (T)elementData[size-1];
    }

    public String toString(){
        StringBuilder sb=new StringBuilder("[");
        for (int i=0;i<size;i++){
            sb.append(elementData[i].toString()+",").toString();
        }
        return sb.append("]").toString();
    }
}
