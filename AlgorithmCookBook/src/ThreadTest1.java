import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceNodeSetData;
import java.sql.Connection;
/**
 * Created by windons8 on 2018/2/11.
 */
public class ThreadTest1 {
    private int j;
    public static void main(String[] arg){
        ThreadTest1 tt=new ThreadTest1();
        Inc inc=tt.new Inc();
        Dec dec=tt.new Dec();
        for (int i=0;i<2;i++){
            Thread t=new Thread(inc);

            t.start();
            t=new Thread(dec);
            t.start();
        }
    }
    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }
    class Inc implements Runnable{
        public void run() {
            for (int i = 0; i < 100; i++)
                inc();
        }
    }
    class Dec implements Runnable{
        public void run(){
            for (int i=0;i<100;i++)
                dec();
        }
    }
}
