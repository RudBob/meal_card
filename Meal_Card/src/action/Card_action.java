package action;

import dao.CardDao;
import Card.Meal_Card;
import javax.smartcardio.Card;
import java.sql.SQLException;
import java.util.Scanner;

public class Card_action {
    public static void addCard1() throws SQLException {
        Scanner input=new Scanner(System.in);
        System.out.println("郑州轻工业饭卡欢迎您的使用！！！");
        Meal_Card card=new Meal_Card();
        while(true){
            System.out.print("请输入您的学号:");
            String id=input.nextLine();
            CardDao cd=new CardDao();
            Meal_Card tempcard = cd.findCard(id);
            if(id.equals("0")){
                System.out.println("输入不合法，请重新输入");
            }else if(tempcard.getId().equals("-1")){
                card.setId(id);
                break;
            }else{
                System.out.println("用户学号已存在，请重新输入");
            }
        }
        System.out.print("请输入您的姓名:");
        card.setName(input.nextLine());
        System.out.print("请输入您的班级名称:");
        card.setClass_name(input.nextLine());
        System.out.print("请输入您的密码（---重要----）:");
        card.setPassword(input.nextLine());
        CardDao cd=new CardDao();
        cd.addCard2(card);
        System.out.println();
    }
    public static void addMoney01(Meal_Card card) throws SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入您要充值的金额");
        int numOfMoney=in.nextInt();
        CardDao cd=new CardDao();
        cd.addMoney(card, numOfMoney);
    }
    public static void queryCardMoney(Meal_Card card){
        System.out.println("饭卡余额:"+card.getMoney());
    }
    public static void queryCard(Meal_Card card){
        System.out.println("您饭卡的完整信息为：");
        System.out.println("班级："+card.getClass_name());
        System.out.println("学号："+card.getId());
        System.out.println("姓名："+card.getName());
    }
    public static Meal_Card find(String id) throws SQLException {
        CardDao cd=new CardDao();
        return  cd.findCard(id);
    }
    public  static  void deleteCard(Meal_Card card) throws SQLException {
        CardDao cd=new CardDao();
        System.out.println("您真的要删除这个饭卡吗？\n 输入 1 确定，其他退出修改");
        Scanner in=new Scanner(System.in);
        if(in.nextLine().equals("1")){
            System.out.println("已经成功删除学号为："+card.getId()+"的用户。");
            cd.deleCard(card);
        }else{
            System.out.println("未删除"+card.getId()+" "+card.getName());
        }
    }
    public static void costCard(Meal_Card card) throws SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入您要花费的金额：");
        double cost=in.nextDouble();
        CardDao cd= new CardDao();
        cd.costCard(card,cost);
    }
    public static void queryBankCard(Meal_Card card){
        System.out.println("银行卡余额为："+card.getNumOfBankCard());
    }
    public static void alterMessage(Meal_Card card) throws SQLException {
        System.out.println("您当前的信息为：");
        queryCard(card);
        System.out.println("您确定要修改信息吗？ 按 1 确认，其他退出修改");
        Scanner in=new Scanner(System.in);
        if(in.nextLine().equals("1")){
            System.out.println("您已确认修改！");
            CardDao cd=new CardDao();
            cd.alter(card);

        }else{
            System.out.println("您放弃了修改个人信息。");
        }
    }
}