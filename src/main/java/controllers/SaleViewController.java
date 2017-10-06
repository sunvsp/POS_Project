/**
 * Sample Skeleton for 'SalePage.fxml' Controller Class
 */

package controllers;

import database.Database;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Cashier;
import models.Product;
import models.LineItem;

import java.io.IOException;

public class SaleViewController {

    @FXML // fx:id="total"
    private Label total; // Value injected by FXMLLoader
    @FXML // fx:id="commentTextField"
    private TextField commentTextField; // Value injected by FXMLLoader
    @FXML // fx:id="paybtn"
    private Button paybtn,btnBack; // Value injected by FXMLLoader
    @FXML
    private Label namePO;
    @FXML
    private Button deleteBtn;


    @FXML // fx:id="tableOrders"
    private TableView<LineItem> tableOrders; // Value injected by FXMLLoader

    @FXML // fx:id="nameOrder"
    private TableColumn<LineItem, String> nameOrder; // Value injected by FXMLLoader
    @FXML // fx:id="qtyOrder"
    private TableColumn<LineItem, String> qtyOrder; // Value injected by FXMLLoader
    @FXML // fx:id="priceOrder"
    private TableColumn<LineItem, String> priceOrder; // Value injected by FXMLLoader


    @FXML // fx:id="tableProducts"
    private TableView<LineItem> tableProducts; // Value injected by FXMLLoader

    @FXML // fx:id="noProduct"
    private TableColumn<LineItem, String> noProduct; // Value injected by FXMLLoader
    @FXML // fx:id="nameProduct"
    private TableColumn<LineItem, String> nameProduct; // Value injected by FXMLLoader
    @FXML // fx:id="priceProduct"
    private TableColumn<LineItem, String> priceProduct; // Value injected by FXMLLoader

    private Cashier cashier;
    private double y;
    private Database database;

    public SaleViewController(){
        cashier = new Cashier();
        cashier.startSale();
        y = (cashier.getProductCatalog().getLineItems().size()*40+25);//*23+25
        database = new Database();
    }


    //setting
    @FXML
    public void initialize(){
        //Order
        nameOrder.setCellValueFactory(cellData -> cellData.getValue().getProduct().getNames());
        qtyOrder.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
        priceOrder.setCellValueFactory(cellData -> cellData.getValue().getProduct().getPrices());

        //Product
        noProduct.setCellValueFactory(cellData -> cellData.getValue().getProduct().getIds());
        nameProduct.setCellValueFactory(cellData -> cellData.getValue().getProduct().getNames());
        priceProduct.setCellValueFactory(cellData -> cellData.getValue().getProduct().getPrices());
        tableProducts.setItems(cashier.getProductCatalog().getLineItems());
        tableProducts.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("hello");
                if(event.getButton() == MouseButton.PRIMARY){
                    if(event.getClickCount() == 2 && event.getSceneY() <= y && cashier.getProductCatalog().getLineItems().size() > 0){
//                    	addProduct(2);
                        createAddQualityDialog();
//                    	System.out.println(event.getSceneX()+" "+event.getSceneY());
                    }

//                    System.out.println(event.getSceneX()+" "+event.getSceneY());

                }
            }
        });

        tableOrders.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(cashier.getSale().getLineItems().size() > 0){
                    handleClickOrder();
                }
            }
        });
    }


    public void addProduct(int qty){
        Product prd = tableProducts.getSelectionModel().getSelectedItem().getProduct();
        if(prd != null){
//            System.out.println(prd.getName());
//            System.out.println(prd.getPrice());
            cashier.addItem(prd, qty); // เธฃเธญเธ–เธฒเธกเธ�เธณเธ�เธงเธ�
        }
        refreshOrders();
        total.setText("        Total:  "+cashier.getSale().getTotal());
    }

    public void handleClickOrder(){

        Product prd = tableOrders.getSelectionModel().getSelectedItem().getProduct();
        if(prd != null){
            namePO.setText("   Product: "+prd.getName()+"");
        }
    }

    public void deletePrd(){
        Product prd = tableOrders.getSelectionModel().getSelectedItem().getProduct();
        if(prd != null){
            cashier.getSale().deleteItem(tableOrders.getSelectionModel().getSelectedItem());
            //System.out.println(cashier.getSale().getLineItems().size());
        }
        refreshOrders();
        namePO.setText("");
        total.setText("        Total:  "+cashier.getSale().getTotal());
    }
    private void createAddQualityDialog() {
    	Dialog<Integer> dialog = new Dialog<>();
    	dialog.setTitle("Quality");
    	dialog.setHeaderText("ใส่จำนวนสินค้าที่ต้องการ");
    	ButtonType logButton = new ButtonType("Add", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.CANCEL);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	grid.add(new Label("Quality"), 0, 0);
    	TextField qty = new TextField();
    	qty.setText("1");
    	grid.add(qty, 0, 1);
    	dialog.getDialogPane().setContent(grid);
    	Platform.runLater(() -> qty.requestFocus());
    	
    	dialog.setResultConverter(dialogButton -> {
    		if (dialogButton==logButton) {
    			addProduct(Integer.parseInt(qty.getText()));
    			return Integer.parseInt(qty.getText());
    		} else {return null;}
    	});
    	dialog.show();
    }

    @FXML
    public void handleButtonPay(){
        ObservableList<LineItem> lineItem = cashier.getSale().getLineItems();
        database.openDatabase();
        for (LineItem l:lineItem) {
            LineItem line = cashier.getProductCatalog().decreaseItem(l);
            //System.out.println(line.getQuantity().get());
            database.decreaseQuantity(line);

        }
        database.closeDatabase();

        cashier.endSale();
        cashier.startSale();
        refreshOrders();
        total.setText("        Total:    0.00");
    }


    @FXML
    public void refreshOrders(){
        tableOrders.setItems(cashier.getSale().getLineItems());
        tableOrders.refresh();
    }

    @FXML
    void actionLogoutBtn(ActionEvent event) throws IOException {
//    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/LogoutPage.fxml"));
//    	pane.setStyle("-fx-background-color: #fffafa;");
//    	adminPane.getChildren().setAll(pane);
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Back to HomePage");
        dialog.setHeaderText("Do you want to Back to HomePage.");
        ButtonType logButton = new ButtonType("Yes", ButtonData.YES);
        dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.NO);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton==logButton) {
                createStartMenu();
                return 300;
            } else {return 505;}
        });
        dialog.show();

    }

    private void createStartMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StartPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("POS project");
            //scene.getStylesheets().add(getClass().getResource("/views/salePage.css").toExternalForm());
            //stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();

            Stage oldStage = (Stage) btnBack.getScene().getWindow();
            oldStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
