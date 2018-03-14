/**
 * Created by windons8 on 2018/3/11.
 */
public class TouTiaoOR {

    private static class trieNode{
        trieNode[] next=new trieNode[2];
        int count=0;
        int digit=0;
        public trieNode(int digit){
            this.digit=digit;
        }
    }


    public static void insert(trieNode root,int number){
        trieNode current=root;
        for(int i=31;i>=0;i--){
            int digit=(number>>i)&i;
            if(current.next[digit]==null){
                current.next[digit]=new trieNode(digit);
            }
            current=current.next[digit];
            current.count++;
        }
    }


    public static int query(trieNode root,int a,int m, int k){
        if(root==null){
            return 0;
        }
        trieNode current=root;

        for(int i=k;i>=0;i--){
            int aDigit=(a>>i)&1;
            int mDigit=(m>>i)&i;

            if(aDigit==1&&mDigit==1){


            }
        }
        return -1;
    }

}
