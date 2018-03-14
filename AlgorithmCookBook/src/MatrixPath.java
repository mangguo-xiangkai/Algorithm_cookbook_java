import java.util.Arrays;

/**
 * Created by windons8 on 2018/3/6.
 */
public class MatrixPath {

    public static boolean hasPath(char[] Matrix, int rows,int cols,char[] str){

        if(Matrix==null||str==null){
            return false;
        }

        boolean[] visited=new boolean[rows*cols];

        Arrays.fill(visited,false);

        int pathLength=0;

        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if(hasPathCore(Matrix,rows,cols,str,i,j,pathLength,visited))
                    return true;
            }
        }
        return false;
    }



    private static boolean hasPathCore(char[] Matrix,int rows,int cols,char[] str,int row,int col, int pathLength,boolean[] visited){
        if(pathLength==str.length){
            return true;
        }

        boolean hasPath=false;
        int location=row*cols+col;
        if(row>=0&&row<rows&&col>=0&&col<cols&&Matrix[location]==str[pathLength]&&!visited[location]) {
            visited[location] = true;
            pathLength++;

            hasPath = (hasPathCore(Matrix, rows, cols, str, row, col - 1, pathLength, visited)
                    || hasPathCore(Matrix, rows, cols, str, row - 1, col, pathLength, visited)
                    || hasPathCore(Matrix, rows, cols, str, row, col + 1, pathLength, visited)
                    || hasPathCore(Matrix, rows, cols, str, row + 1, col, pathLength, visited));
            if (!hasPath) {
                pathLength--;
                visited[location] = false;
            }
        }
        return hasPath;

    }


    public static void main(String[] args){
        // AUQE
        // DIGU
        // FJIU
        // TRET
        //  有迷惑 项 ，，程序并没有 找到 最佳答案
       System.out.println(hasPath("ABCEDFGUHJITTREQ".toCharArray(),4,4,"FJIUQ".toCharArray()));

    }






}
