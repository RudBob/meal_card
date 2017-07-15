package dao;


import Card.Meal_Card;
import DB.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Created by user on 17-7-15.
 */
public class CardDao {
    public static void addCard2(Meal_Card mc) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "" +
                "insert into card" +
                "(id, name, class_name,password)" +
                "values(" +
                "?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, mc.getId());
        ptmt.setString(2, mc.getName());
        ptmt.setString(3, mc.getClass_name());
        ptmt.setString(4, mc.getPassword());
        ptmt.execute();

    }

    public static Meal_Card findCard(String id) throws SQLException {//通过传入的id进行查询是否存在饭卡，并将存在的饭卡作为返回值
        Connection conn = JDBC.getConnection();
        String sql = "" +
                "select * from card where id=? ";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, id);
        ResultSet rs = ptmt.executeQuery();
        Meal_Card card = new Meal_Card();
        if (rs.next()) {
            card.setId(rs.getString("id"));
            card.setName(rs.getString("name"));
            card.setClass_name(rs.getString("class_name"));
            card.setMoney(rs.getFloat("money"));
            card.setPassword(rs.getString("password"));
            card.setNumOfBankCard(rs.getDouble("numOfBankCard"));
        } else {
            card.setId("-1");
        }
        return card;
    }

    public static double addMoney(Meal_Card card, Integer add) throws SQLException {
        Connection conn = JDBC.getConnection();
        if (card.getNumOfBankCard() < add) {
            System.out.println("银行卡余额已不足，请及时到银行充值！！");
        } else {
            card.setMoney(card.getMoney() + (double) add);  //饭卡余额
            card.setNumOfBankCard(card.getNumOfBankCard() - (double) add); //银行卡余额
            String sql = "" +
                    "update card " +
                    "set money=? , numOfBankCard=? " +
                    "where id= ? ";
            PreparedStatement ptmt = conn.prepareStatement(sql);

            ptmt.setFloat(1, (float) card.getMoney());
            ptmt.setFloat(2, (float) card.getNumOfBankCard());
            ptmt.setString(3, card.getId());
            ptmt.execute();
            System.out.println("充值成功，您卡上的余额为：" + card.getMoney());
        }
        return card.getMoney();
    }

    public static void deleCard(Meal_Card card) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "" +
                "delete from card  " +
                "where id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, card.getId());
        ptmt.execute();

    }

    public static void costCard(Meal_Card card, double cost) throws SQLException {
        Connection conn = JDBC.getConnection();
        if (card.getMoney() < cost) {
            System.out.println("饭卡余额已不足，请及时充值！！");
        } else {
            card.setMoney(card.getMoney() - cost);  //饭卡余额
            String sql = "" +
                    "update card " +
                    "set money=? " +
                    "where id= ? ";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setFloat(1, (float) card.getMoney());
            ptmt.setString(2, card.getId());
            ptmt.execute();
            System.out.println("你已消费" + cost + "元,卡上余额为" + card.getMoney());
        }
    }

    public static void alter(Meal_Card card) throws SQLException {
        System.out.println("请重新输入个人信息：");
        String temp = card.getId();
        Scanner in = new Scanner(System.in);
        System.out.println("输入您新的学号");
        card.setId(in.nextLine());
        System.out.println("输入您新的用户名");
        card.setName(in.nextLine());
        System.out.println("输入您新的密码");
        card.setPassword(in.nextLine());
        System.out.println("输入您新的班级名称");
        card.setClass_name(in.nextLine());
        Connection conn = JDBC.getConnection();
        String sql = "" +
                "UPDATE card set id=?, name=?,class_name=?,password=?" +
                "where id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, card.getId());
        ptmt.setString(2, card.getName());
        ptmt.setString(3, card.getClass_name());
        ptmt.setString(4, card.getPassword());
        ptmt.setString(5, temp);
        ptmt.execute();
    }
}
