package TreeClass;
/**
 * Created by windons8 on 2017/11/16.
 */
public class ArrayBinaryTree <T>{
    private Object[] datas;
    public int deep;
    private int arraySize;
    private int DEFAULT_DEEP=9;
    public ArrayBinaryTree(){
        this.deep=DEFAULT_DEEP;
        this.arraySize=(int)Math.pow(2,deep)-1;
        datas=new Object[arraySize];
    }
    public ArrayBinaryTree(int deep){
        this.deep=deep;
        this.arraySize=(int)Math.pow(2,deep)-1;
        datas=new Object[arraySize];
    }
    public ArrayBinaryTree(int deep,T data){
        this.deep=deep;
        this.arraySize=(int)Math.pow(2,deep)-1;
        datas=new Object[arraySize];
        datas[0]=data;
    }
    public void add(int index,T data,boolean left){
        if(datas[index]!=null){
            ensurecapatity();
            if(left==true){
                datas[2*index]=data;
            }else{
                datas[2*index+1]=data;
            }
        }else{
            throw new RuntimeException("此节点为空节点，不能添加子节点");
        }
    }
    public boolean isempty(){
        return datas[0]==null;
    }
    public T root(){
        return (T)datas[0];
    }
    public T parent(int index){
        return (T)datas[(index-1)/2];
    }
    public T left(int index){
        if(index==0){
            return (T)datas[1];
        }else{
            int te=2*index+1;
            if(te<arraySize&&datas[te]!=null){
                return (T)datas[te];
            }else
                return null;
        }
//        return null;
    }
    public T right(int index){
        if(index==0){
            return (T)datas[2];
        }   else{
            int te=2*index+2;
            if(te<arraySize&&datas[te]!=null){
                return (T)datas[te];
            }else
                return null;
        }
    }
//    public int childdeep(int index){
//
//    }
    private void ensurecapatity() {
        if(datas[arraySize]!=null){
            deep=deep+1;
            arraySize=(int)Math.pow(2,deep)-1;
        }
    }


}
