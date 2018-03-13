import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;

/**
 * Created by windons8 on 2018/3/5.
 */
public class Craps {


    public static int rollDice(){
        int roll=((int)(Math.random()*6)+1+(int)(Math.random()*6)+1);
        if(roll<2){
            roll=2;
        }
        return roll;
    }



    public static void main(String[] args) throws IOException{

        final int WIN=0,LOST=1,CONTINUE=2;

        boolean firstRoll=true;
        int gameStatus=CONTINUE;
        int die1,die2,sumOfDice;

        int firstPoint=0;

        System.out.println("Craps 赌博游戏 按Enter 键开始：");

        while (true){
            System.in.read();
            if(firstRoll){
                sumOfDice=rollDice();
                System.out.println("\n 玩家掷出的点数和为："+sumOfDice);
                switch (sumOfDice){
                    case 7:
                    case 11:
                        gameStatus=WIN;
                        break;
                    case 2:
                    case 3:
                    case 12:
                        gameStatus=LOST;
                        break;
                    default:
                        firstRoll=false;
                        gameStatus=CONTINUE;
                        firstPoint=sumOfDice;
                        break;
                }
            }else {
                sumOfDice=rollDice();
                System.out.println("\n 玩家掷出的点数和："+sumOfDice);
                if(sumOfDice==firstPoint){
                    gameStatus=WIN;
                }else if(sumOfDice==7){
                    gameStatus=LOST;
                }
            }
            if(gameStatus==CONTINUE){
                System.out.println("未分胜负，再掷一次");
            }else {
                if(gameStatus==WIN){
                    System.out.println("玩家胜");
                }else {
                    System.out.println("玩家 输");
                }
                System.out.println("在玩一次？");
                if(System.in.read()=='n'){
                    System.out.println("游戏结束");
                    break;
                }
            }
        }
    }
}
