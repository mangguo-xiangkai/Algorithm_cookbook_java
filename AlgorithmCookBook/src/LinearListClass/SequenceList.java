package LinearListClass;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by windons8 on 2017/11/14.
 */
public class SequenceList <T> extends ListClass{
        private int DEFAULT_SIZE=16;
        private int capacity;
        private Object[] elementData;
        private int size=0;
        public SequenceList(){
            capacity=DEFAULT_SIZE;
            elementData=new Object[capacity];
        }
        public SequenceList(T element){
            this();
            elementData[0]=element;
            size++;
        }
        public SequenceList(T element,int initSize){
            capacity=1;
            while (capacity<initSize){
                capacity<<=1;
            }
            elementData=new Object[capacity];
            elementData[0]=element;
            size++;
        }

        //获取线性表第 i 处的元素
        @SuppressWarnings("Unchecked")
        public T get(int i){
//            if (i<0||i>size-1){
//                throw new IndexOutOfBoundsException("线性表索引越界");
//            }
            indexoutbound(i);
            return (T)elementData[i];
        }
        //  查找线性表 制定元素 的 索引
        public int loacte(T element){
            for (int i=0;i<size;i++){
                if (element==elementData[i])
                    return i;
            }
            return -1;//  改成抛出   没有找到
        }
        // 插入元素，，在指定位置
        public void insert(T element,int index){
            if (index<0||index>size){
                throw new IndexOutOfBoundsException("线性表索引越界");
            }
            ensureCapcity(size-1);
            System.arraycopy(elementData,index,elementData,index+1,size-index);
            elementData[index]=element;
            size++;
        }
        public void indexoutbound(int i){
            if (i<0||i>size-1){
                throw new IndexOutOfBoundsException("线性表索引越界");
            }
        }
        public void ensureCapcity(int minCapacity){
            if (minCapacity>capacity){
                while(capacity<minCapacity){
                    capacity<<=1;
                }
                elementData = Arrays.copyOf(elementData,capacity);
            }
        }
        public void add(T element){
            insert(element,size);
        }

        public int length(){
            return this.size;
        }
        // 删除 线性表 指定索引出的 值
        //  为什么要有返回值，，可以没有哎
        @SuppressWarnings("Unchecked")
        public T delete(int index){
            indexoutbound(index);
            T oldValue=(T)elementData[index];
            int numMoved=size-index-1;
            if(numMoved>0){
                System.arraycopy(elementData,index+1,elementData,index,numMoved);
            }
            elementData[--size]=null;//  内存，
            return oldValue;
        }
        public T remove() {
            return delete(size-1);
        }
        public boolean empty(){
            return size==0;
        }
        public void clear(){
            Arrays.fill(elementData,null);
            size=0;
        }
        public String toString(){
            if(size==0){return "[]";}
            else{
                StringBuilder sb=new StringBuilder("[");
                for (int i=0;i<size;i++){
                    sb.append(elementData[i].toString()+",");
                }
                int len=sb.length();
                return sb.delete(len-2,len).append("]").toString();
            }
        }
}

