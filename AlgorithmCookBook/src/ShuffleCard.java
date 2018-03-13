import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by windons8 on 2018/3/4.
 */
public class ShuffleCard {

    public static void main(String[] args){
        final int N=52;
        int[] poker=new int[N+1];
        for (int i=1;i<=N;i++){
            poker[i]=i;
        }
        for(int i=1;i<=N;i++){
            int j=(int)(Math.random()*N);
            if(j==0){j=1;}
            int tmp=poker[i];
            poker[i]=poker[j];
            poker[j]=tmp;
        }
        for(int i=1;i<=N;i++){
            switch ((poker[i]-1)/13){
                case 0:System.out.println("桃");break;
                case 1:System.out.println("心");break;
                case 2:System.out.println("砖");break;
                case 3:System.out.println("梅");break;
            }
            int remain=poker[i]%13;
            switch (remain){
                case 0:System.out.println("K");break;
                case 1:System.out.println("Q");break;
                case 2:System.out.println("J");break;
                default:System.out.println(remain+" ");break;
            }
            if(i%13==0){
                System.out.println(" ");

            }

        }


//        for (int i=1;i<poker.length;i++){
//            System.out.println(poker[i]);
//        }
    }
}
