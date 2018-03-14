import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * Created by windons8 on 2018/3/5.
 */
public class MaxInWindows {



    public List<Integer> sulution1(List<Integer> data,int size){
        List<Integer> windowsaMax=new LinkedList<Integer>() ;

        if(data==null||size<1||data.size()<1){
            return windowsaMax;
        }

        Deque<Integer> idx=new LinkedList<>();

        for(int i=0; i<size&&i<data.size();i++){
            while (!idx.isEmpty()&&data.get(i)>=data.get(idx.getLast())){
                idx.removeLast();
            }
            idx.addLast(i);
        }

        for(int i=size;i<data.size();i++){
            windowsaMax.add(data.get(idx.getFirst()));
            while (!idx.isEmpty()&&data.get(i)>=data.get(idx.getLast())){
                idx.removeLast();
            }
            if(!idx.isEmpty()&&idx.getFirst()<=(i-size)){
                idx.removeFirst();
            }
            idx.addLast(i);
        }

        windowsaMax.add(data.get(idx.getFirst()));

        return windowsaMax;
    }



    private static List<Integer> arraysToCollection(int[] array){
        List<Integer> list=new LinkedList<>();
        for(int a:array){
            list.add(a);
        }
        return list;
    }


    public static void main(String[] args){
//        List<Integer> de=(LinkedList)Arrays.asList(new int[]{1,4,5,4,5});


        List<Integer> d1=arraysToCollection(new int[]{1,5,12,3,4,5,7,89,45,12,1});

        System.out.print(d1+","+new MaxInWindows().sulution1(d1,4));

    }
}
