import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by windons8 on 2018/3/6.
 */
public class NumThanHalfofArray<T> {




    public List<T> solution1(T[] data){
        Map<T,Integer> map=new HashMap<>();

        for (T dd:data){
            if(map.containsKey(dd)){
                map.put(dd,map.get(dd)+1);
            }else {
                map.put(dd,1);
            }
        }
        List<T> list=new ArrayList<>();
        for ( T md:map.keySet()){
            if(map.get(md)>=(2))
                list.add(md);
        }
        return list;
    }

    public static void main(String[] args){
        new NumThanHalfofArray<>().solution1(new Integer[]{1,23,4,45,48,23,45,1,5,1,4,1,8,1,5,1,1,12,1,1,1,6}).forEach(
                x->System.out.print(x+" ")
        );
    }
}
