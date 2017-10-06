package models;

import javafx.beans.property.SimpleStringProperty;

public class LineItem {
    private  Product product;
    private  int quantity;

    public LineItem(Product prd, int quantity){
        product = prd;
        this.quantity = quantity;
    }
    public double getSubTotal(){
        return product.getPrice()*quantity;
    }

    public Product getProduct() {
        return  product;
    }

    public SimpleStringProperty getQuantity(){
        return new SimpleStringProperty(quantity+"");
    }

    public void setQuantity(int qty){
        quantity = qty;
    }
}
