package Card;


public class Meal_Card {
    private String id;
    private String name;
    private String class_name;
    private double money;
    private String password;
    private double numOfBankCard;
    public void setNumOfBankCard(double numOfBankCard) {
        this.numOfBankCard = numOfBankCard;
    }

    public double getNumOfBankCard() {
        return numOfBankCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getClass_name() {
        return class_name;
    }

    public double getMoney() {
        return money;
    }

}