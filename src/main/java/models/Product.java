package models;

import javafx.beans.property.SimpleStringProperty;

public class Product {
    private int id;
    private String name;
    private double price;

    public  Product (int id,String name,double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public SimpleStringProperty getIds(){
        return  new SimpleStringProperty(id+"");
    }

    public SimpleStringProperty getNames(){
        return  new SimpleStringProperty(name);
    }

    public SimpleStringProperty getPrices(){
        return  new SimpleStringProperty(price+"");
    }


    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
