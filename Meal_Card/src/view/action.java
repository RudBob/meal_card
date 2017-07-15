package view;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import action.Card_action;
import Card.Meal_Card;

public class action {
    /**
     * 
     *
     * @throws InterruptedException
     * @throws SQLException
     */
    public static void main(String[] args) throws InterruptedException, SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("----------------------欢迎进入郑州轻工业饭卡业务系统---------------------");
        Integer power=0;  //权限，power为 10 时为管理员权限，power为 0 时为用户权限
        Loop:
        while(true){
            System.out.println(" 1.登录界面，请按 1 \n 2.注册界面，请按 2 \n 0.退出系统，请按 0 ");
            if(power==10){
                System.out.println(" 9.退出管理员身份，请输入 9 ");
            }
            String flag=in.nextLine();  //通过判断输入的值，来确定用户接下来的操作
            power=admin(flag, power);//管理员权限登录及回收
            if(flag.equals("1")) { //输入“2”，首先先确定用户的卡号，然后，再进行其他操作。
                Meal_Card card=null;  //顶替一张新卡，看能否指向
                while (true) {
                    if(power!=10){
                        System.out.print("请输入你的学号：");//通过学号查询，调用CardDao的findCard方法
                    }
                    else{
                        System.out.print("请输入您要操作的目标学号：");
                    }
                    String id0 = in.nextLine().trim();
                    if(id0.equals("0")){  //若输入的是0，则退出
                        System.out.println("退回主界面\n");
                        break Loop;
                    }
                    Card_action ca=new Card_action();
                    card=ca.find(id0);//通过输入的id得到一个旧卡的属性，
                    // 如果id不合法，则令旧卡id=“-1”，使其报错
                    if(card.getId().equals("-1")){
                        System.out.println("----------------------您输入的用户不存在----------------------");
                        System.out.println("请重新输入用户名，或输入 0 退出主界面");
                    }else{
                        break;  //成功查找到该用户，进入密码验证
                    }
                }
                while (true){
                    if(power==0){
                        System.out.print("请输入您的密码: ");
                    }else {
                        System.out.println("管理员输入回车即可强制登录。");
                    }
                    String t=in.nextLine().trim();
                    if(power==10){  //判断是否是管理员权限
                        System.out.println("管理员强制登录中。。。。");
                        Thread.sleep(1000);
                        System.out.println("----------------------管理员强制登录成功----------------------------");
                        break;
                    }else if(card.getPassword().equals(t)){ //判断用户输入的密码是否正确，正确则进入下一步
                        break;
                    }else { //既不是管理员，密码输入又错误，那么需要重新输入
                        System.out.println("您的密码输入错误，请重新输入.\n");
                    }
                }System.out.println("---------------------------欢迎使用饭卡系统-----------------------");
                while(true){
                    if(power == 10){
                        System.out.println("0.注销饭卡                    请按 0 (仅管理员可见)");
                    }
                    System.out.println("1.充值饭卡.                   请按 1 ");
                    System.out.println("2.查询余额.                   请按 2 ");
                    System.out.println("3.查询信息.                   请按 3 ");
                    System.out.println("4.饭卡消费.                   请按 4 ");
                    System.out.println("5.查询银行卡上余额             请按 5 ");
                    System.out.println("6.修改信息.                   请按 6 ");
                    System.out.println("7.退出系统.                   请按 7 ");

                    String k=in.nextLine();//判断用户输入的值
                    Card_action cdan=new Card_action();//新new一个Card_action对象，便于调用其内部方法
                    if(power==10&&k.equals("0")){
                        cdan.deleteCard(card);  //调用删除用户方法，进行删除
                    }else if(k.equals("1")){
                        cdan.addMoney01(card); //调用充钱方法
                    }  else if(k.equals("2")){
                        cdan.queryCardMoney(card);
                    }else if(k.equals("3")){
                        cdan.queryCard(card);
                    }else if(k.equals("4")){
                        cdan.costCard(card);
                    }else if(k.equals("5")){
                        cdan.queryBankCard(card);
                    }else if(k.equals("6")){
                        cdan.alterMessage(card);
                    }else if(k.equals("7")){
                        System.out.println("退回主界面\n");
                        break;
                    }else{
                        prinError();
                    }
                }
            } else if(flag.equals("2")){  //输入“1”，所以办理一张饭卡，调用Card_action的addCard1方法新增card
                Card_action cm=new Card_action();
                cm.addCard1();
                System.out.println("您办理饭卡余额为100.0元\n");
            } else if(flag.equals("0")){//输入“0”，退出系统。
                prinEnd();
                break;
            } else if(!flag.equals("admin")){ //输入错误，提醒用户重新输入
                prinError();
            }
        }
    }
    public static void prinError(){
        System.out.println("您的输入不合法，请重新输入.");
    }
    public static void prinEnd(){
        System.out.println("--------期待您的下次光临！--------");
    }
    public static int  admin(String flag, int power) throws InterruptedException {
        if(flag.equals("admin")){
            Scanner in=new Scanner(System.in);
            System.out.print("请输入管理员密码:");
            String pass=in.nextLine();
            if(pass.equals("000000")){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\n");//设置日期格式
                System.out.println("管理员,您好，现在是"+df.format(new Date()));
                return 10;
            }else{
                System.out.println("密码错误，未得到管理员权限！！");
            }
        }else if(flag.equals("9")){
            Thread.sleep(1000);
            System.out.println("-------您已成功退出管理员系统---------");
            return 0;
        }
        return power;
    }
}