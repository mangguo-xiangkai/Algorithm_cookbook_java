/**
 * Created by windons8 on 2018/3/4.
 */




class Fruit{
    private String name;
    private int size;
    private int price;

    public Fruit(String name, int size, int price){
        this.name=name;
        this.size=size;
        this.price=price;
    }

    public String getName(){
        return this.name;
    }
    public int getSize(){
        return this.size;
    }
    public int getPrice(){
        return this.price;
    }
}
public class Knapsacks {
    public static void main(String[] agrs){
        final int MAX=8;
        final int MIN=1;
        int[] value=new int[MAX+1];
        int[] item=new int[MAX+1];

        Fruit fruits[]={new Fruit("李子",4,4500),
                       new Fruit("苹果",5,5700),
                       new Fruit("橘子",2,2250),
                       new Fruit("草莓",1,1100),
                       new Fruit("甜瓜",6,6700)};
        for(int i=0;i<fruits.length-1;i++){
            for(int s=fruits[i].getSize();s<=MAX;s++){
                int p=s-fruits[i].getSize();
                int newvalue=value[p]+fruits[i].getPrice();
                if(newvalue>value[s]){
                    value[s]=newvalue;
                    item[s]=i;
                }
            }
        }
        System.out.println("物品\t价格");
        for (int i=MAX;i>=MIN;i=i-fruits[item[i]].getSize()){
            System.out.println(fruits[item[i]].getName()+"\t"+fruits[item[i]].getPrice());
        }
        System.out.println("合计\t"+value[MAX]);

    }


}
