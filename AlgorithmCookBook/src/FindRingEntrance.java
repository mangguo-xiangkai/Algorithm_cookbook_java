/**
 * Created by windons8 on 2018/3/7.
 */

import LinearListClass.SingleLinkList;


import java.util.LinkedList;
import java.util.List;

public class FindRingEntrance<T> {


    public SingleLinkList<T>.Node solution1(SingleLinkList<T> linkList){
        SingleLinkList<T>.Node i=linkList.getHeader();
        SingleLinkList<T>.Node j;
        j=i;
        while (i.hasNext()) {
            while (j.hasNext()) {
                j = j.next;
                if (j == i) {
                    return i;
                }
            }
            i=i.next;
        }
        return null;

    }



    public static void main(String[] args){
        SingleLinkList<String> test=new SingleLinkList<String>();
        test.add("0");
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        test.add("6");
        test.getNodeByIndex(6).next=test.getNodeByIndex(2);


       System.out.println(new FindRingEntrance().solution1(test));

    }




}
