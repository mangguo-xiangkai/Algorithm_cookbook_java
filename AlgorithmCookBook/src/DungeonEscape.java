import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/8.
 */
public class DungeonEscape {


    public static int solution1(boolean[][] maze,boolean[][] visit,int[] start, int[] end,int[][] dirs){
        Queue<int[]> queue=new LinkedList<>();

        int[] now,next;
        int[] node=new int[]{start[0],start[1],0};
        queue.add(node);

        visit[start[0]][start[1]]=true;

        int maxStep=0;

        while (!queue.isEmpty()){
            now=queue.poll();

            if(maxStep<now[2])maxStep=now[2];

            for(int[] dir:dirs){
                next=new int[]{dir[0]+now[0],dir[1]+now[1],now[2]+1};
                if(next[0]<0||next[0]>=maze.length||next[1]<0||next[1]>=maze[0].length||visit[next[0]][next[1]]==true||maze[next[0]][next[1]]==false){
                    continue;
                }
                queue.add(next);
                visit[next[0]][next[1]]=true;
            }
        }

        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                if(visit[i][j]==false&&maze[i][j]==true) return -1;
            }
        }

        return maxStep;

    }


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        while (sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();

            boolean[][] maze=new boolean[n][m];
            for(int i=0;i<n;i++){
                String s=sc.next();
                for(int j=0;j<m;j++){
                    if(s.charAt(j)=='.')
                        maze[i][j]=true;
                }
            }

            int[] start=new int[]{sc.nextInt(),sc.nextInt()};

            int ndir=sc.nextInt();

            int[][] dirs=new int[ndir][2];

            for(int i=0;i<ndir;i++){
                dirs[i][0]=sc.nextInt();
                dirs[i][1]=sc.nextInt();
            }
            boolean[][] visit=new boolean[n][m];

            System.out.println(solution1(maze,visit,start,new int[]{n-1,m-1},dirs));
        }
        sc.close();
    }





}
