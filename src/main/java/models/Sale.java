package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Sale {
    private ObservableList<LineItem> lineItemSale = FXCollections.observableArrayList();


    public double getTotal(){
        double total = 0;
        for (LineItem s : lineItemSale) {
            total += s.getSubTotal();
        }
        return total;
    }

    public void makeNewLine(Product prd , int qty){
        lineItemSale.add(new LineItem(prd,qty));
    }

    public void deleteItem(LineItem prd){
        lineItemSale.remove(prd);
    }


    public ObservableList<LineItem> getLineItems(){
        return lineItemSale;
    }
}
