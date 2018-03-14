package SortedClass;

/**
 * Created by windons8 on 2017/11/18.
 */
public class DataWarp implements Comparable<DataWarp>{
    public int data;
    String flag;
    public DataWarp(int data,String flag){
        this.data=data;
        this.flag=flag;
    }
    public String toString(){
        return data+flag;
    }
    public int compareTo(DataWarp dw){

        return this.data>dw.data?1:(this.data==dw.data?0:-1);
    }
}
