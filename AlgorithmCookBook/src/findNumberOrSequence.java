import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by windons8 on 2018/3/6.
 */
public class findNumberOrSequence {

    public static List<List<Integer>> solution1(int[] data, int sum){
        List<List<Integer>> result=new ArrayList<>();

        Map<Integer,Integer> map=new HashMap<>();
        int i=0;
        for (int a:data){
            if(map.containsValue(sum-a)){
                List<Integer> tem=new ArrayList<>();
                tem.add(a);
                tem.add(sum-a);
                result.add(tem);
            }else {
                map.put(i,a);
            }
            i++;
        }
        return result;
    }


    public static List<List<Integer>> solution2(int[] data,int sum){
        List<List<Integer>> result=new ArrayList<>();

        for (int i=0;i<data.length;i++){
            for(int j=i+1;j<data.length-1;j++){
                ArrayList<Integer> list=new ArrayList<>();
                int temsum=0;
                for(int k=i;k<=j;k++){
                    temsum=data[k]+temsum;
                    list.add(data[k]);
                }
                if(temsum==sum){
                    result.add(list);
                    continue;
                }else if(temsum>sum){
                    continue;
                }
            }
        }
        return result;

    }



    public static List<List<Integer>> solution3(int[] data,int sum){

        List<List<Integer>> result=new ArrayList<>();
        if(sum<3){
            return result;
        }
        int small=1;
        int big=2;

        int middle=(1+sum)/2;

        int cursum=small+big;

        while (small<middle){
            if(cursum==sum){
                List<Integer> list=new ArrayList<>();
                for(int i=small;i<=big;i++){
                    list.add(i);
                }
                result.add(list);
            }
            while (cursum>sum&&small<middle){
                cursum-=small;
                small++;
                if(cursum==sum){
                    List<Integer> list=new ArrayList<>();
                    for(int i=small;i<=big;i++){
                        list.add(i);
                    }
                    result.add(list);
                }
            }
            big++;
            cursum+=big;

        }
        return result;

    }








    public static void main(String[] args){

        findNumberOrSequence.solution1(new int[]{1,2,3,4,5,6,7,8,9,10},14).forEach(
                x->System.out.print(x)
        );
        findNumberOrSequence.solution2(new int[]{1,2,3,4,5,6,7,8,9,10},15).forEach(
                x->System.out.print("\n "+x)
        );

    }


}
