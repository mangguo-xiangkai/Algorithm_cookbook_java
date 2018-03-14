import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by windons8 on 2018/3/6.
 */
public class PrintMatrixInCircle {


    public static List<List<Integer>> solution1(int[][] matrix){
        List<List<Integer>> list=new ArrayList<>();
        int n=0;
        int m=matrix.length;
        int start=0;
        while (n>start*2&&m>start*2){
            List<Integer> tem=printMatrixInCricle(matrix,m,n,start);
            list.add(tem);
            start++;
        }
        return list;
    }

    private static List<Integer> printMatrixInCricle(int[][] matrix,int rows, int cols, int start){
        List<Integer> list=new ArrayList<>();
        int endX=cols-1-start;
        int endY=rows-1-start;

        for(int i=start;i<=endX;i++){
            list.add(matrix[start][i]);
        }
        if(start<endY){
            for(int i=start+1;i<=endY;i++){
                list.add(matrix[i][endX]);
            }
        }

        if(start<endX&&start<endY){
            for(int i=endX-1;i>=start;i--){
                list.add(matrix[endY][i]);
            }
        }

        if(start<endX&&start<endY-1){
            for(int i=endY-1;i>=start+1;i--){
                list.add(matrix[i][start]);
            }
        }

        return list;

    }


    public static List<Integer> solution2(int[][] matrix){
        if(matrix==null){return null;}
        List<Integer> list=new ArrayList<>();
        int x=0;
        int y=0;
        while (x*2<matrix.length&&y*2<matrix[0].length){
            printMatrixInCricle(matrix,x,y,list);
            x++;
            y++;
        }
        return list;
    }

    private static void printMatrixInCricle(int[][] matrix,int x,int y,List<Integer> list){
        int rows=matrix.length;
        int cols=matrix[0].length;
        for(int i=y;i<=cols-y-1;i++){
            list.add(matrix[x][i]);
        }
        if(rows-x-1>x){
            for(int i=x+1;i<=rows-x-1;i++){
                list.add(matrix[i][cols-y-1]);
            }
        }
        if((rows-x-1)>x&&(cols-1-y)>y){
            for(int i=cols-y-2;i>=y;i--){
                list.add(matrix[rows-1-x][i]);
            }
        }

        if(cols-1-y>y&&rows-1-x>x+1){
            for(int i=rows-1-x-1;i>=x+1;i--){
                list.add(matrix[i][y]);
            }
        }
//        return list;
    }





    public static void main(String[] args){

        PrintMatrixInCircle.solution1(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}).forEach(
                x->System.out.print(x+" ")
        );
       System.out.println();



    }






}
