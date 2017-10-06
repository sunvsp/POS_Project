package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sound.sampled.Line;


/**
 * เก็บสินค้าทั้งหมดในร้านของเรา
 * @author
 * @version
 */
public class ProductCatalog {
    private ObservableList<LineItem> lineItemCat = FXCollections.observableArrayList();

    private static ProductCatalog instacnce = null;

    public static ProductCatalog getInstacnce() {
        if(instacnce == null){
            instacnce = new ProductCatalog();
        }
        return instacnce;
    }
//
// hello




    /**
     * add lineItem to arrayList
     * @param lineItem
     */
    public void addItem(LineItem lineItem){
        lineItemCat.add(lineItem);
    }

    public void deleteItem(LineItem lineItem){
        lineItemCat.remove(lineItem);
    }

    public LineItem decreaseItem(LineItem lineItem){
        for (LineItem l: lineItemCat) {
           if(l.getProduct().getId() == lineItem.getProduct().getId() ) {
               l.setQuantity(Integer.parseInt(l.getQuantity().get()) - Integer.parseInt(lineItem.getQuantity().get()));
               return l;
           }
        }
        return null;

    }




    public ObservableList<LineItem> getLineItems() {
        return lineItemCat;
    }
    public void setLineItem(ObservableList<LineItem> l){
        lineItemCat = l;
    }
}
