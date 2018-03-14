import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by windons8 on 2018/3/9.
 */
public class XiaKitchen {


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);


        HashSet<String> martic=new HashSet<>();
        while (sc.hasNext()){
            martic.add(sc.next());
        }
        System.out.println(martic.size());


    }
}
