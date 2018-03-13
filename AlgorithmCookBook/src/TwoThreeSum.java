import java.util.*;

/**
 * Created by windons8 on 2018/3/2.
 */
public class TwoThreeSum {

    public int[] TwoSolution1(int[] nums,int target){
        Map<Integer,Integer> map= new HashMap<>();
        for (int i=0; i<nums.length;i++){
            Integer index=map.get(target-nums[i]);
            System.out.println(index);
            if(index==null){
                map.put(nums[i],i);
            }else {
                return new int[]{i,index};
            }
        }
        return new int[]{0,0};
    }

    public int[] TwoSolution2(int[] nums,int target){

        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if ((nums[i]+nums[j])==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }

    public List<List<Integer>> ThreeSolution1(int[] nums){

        List<List<Integer>> res=new ArrayList<>();

        Arrays.sort(nums);

        Map<Integer,List<Integer>> map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])==null){
                List<Integer> subscripts=new ArrayList<>();
                subscripts.add(i);
                map.put(nums[i],subscripts);
            }else {
                map.get(nums[i]).add(i);
            }
        }
        for(int i=0; i<=nums.length-3;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for (int j=i+1;j<nums.length-2;j++){
                if(j>i+1&& nums[j]==nums[j-1]){
                    continue;
                }
                int finalNum=-nums[i]-nums[j];
                if(finalNum<nums[j]){
                    break;
                }
                List<Integer> subscripts=map.get(finalNum);

                if(subscripts==null){
                    continue;
                }
                for(Integer subscript: subscripts){
                    if(subscript!=j&&subscript!=i){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[subscript]);
                        res.add(list);
                    }
                }
            }

        }
        return res;
    }



    public List<List<Integer>> ThreeSolution2(int[] nums){
        List<List<Integer>> ret=new ArrayList<>();

        int len=nums.length,tar=0;

        if(len<2){ return ret;}

        Arrays.sort(nums);

        for(int i=0;i<=len-3;i++){
            int j=i+1;
            int k=len-1;
            while (j<k){
                if(nums[i]+nums[j]+nums[k]<0){
                    ++j;
                }else if(nums[i]+nums[j]+nums[k]>0){
                    --k;
                }else {
                    ret.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    ++j;
                    --k;
                    while (j<k&&nums[j]==nums[j-1]){++j;}
                    while (j<k&&nums[k]==nums[k+1]){--k;}
                }
            }
//            while (j<len-3&&nums[i]==nums[i+1]){--k;}
        }
        return ret;
    }


    public static void main(String[] args){
        int[] inter={0,1,2,3,4,5,6,7,8,9,14};

        TwoThreeSum TS=new TwoThreeSum();

        int[] cde=TS.TwoSolution1(inter,15);
        System.out.println("===============");
        for (int i=0;i<cde.length;i++){
            System.out.println(cde[i]);
        }

        int[] threetest={-1,0,1,2,-1,-4};
        TS.ThreeSolution1(threetest).forEach(
                x->System.out.println(x)
        );
        TS.ThreeSolution2(threetest).forEach(
                x->System.out.println(x)
        );


    }
}
