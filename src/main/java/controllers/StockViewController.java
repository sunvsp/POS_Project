package controllers;

import database.Database;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Cashier;
import models.LineItem;
import models.Product;
import models.ProductCatalog;

public class StockViewController {
	@FXML private Button addStockBtn;
	@FXML private TableView<LineItem> tableStock;
	@FXML private TableColumn<LineItem, String> idStock, nameStock, priceStock, qtyStock;
	private ProductCatalog productCatalog ;
	private double y;
	private Database database;



	public StockViewController(){
		productCatalog = ProductCatalog.getInstacnce();
		y = (productCatalog.getLineItems().size()*30+65);
		database = new Database();
	}
	
	@FXML
	public void initialize() {

		idStock.setCellValueFactory(cellData -> cellData.getValue().getProduct().getIds());
		nameStock.setCellValueFactory(cellData -> cellData.getValue().getProduct().getNames());
		qtyStock.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
        priceStock.setCellValueFactory(cellData -> cellData.getValue().getProduct().getPrices());
		tableStock.setItems(productCatalog.getLineItems());
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Edit");
		cm.getItems().add(mi1);
		MenuItem mi2 = new MenuItem("Delete");
		cm.getItems().add(mi2);
		mi1.setOnAction(event -> actionEdit());
		mi2.setOnAction(event -> actionDelete());
		tableStock.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY)
				{
					if(event.getSceneY() <= y)
						cm.show(tableStock , event.getScreenX() , event.getScreenY());
					// System.out.println("Right Click!!!");
				}else{
					cm.hide();
					// System.out.println("Left Click!!!!");
				}
			}
		});
	}


	public void actionEdit(){
		LineItem lineItem = tableStock.getSelectionModel().getSelectedItem();
		if(lineItem != null){
			Dialog<Integer> dialog = new Dialog<>();
			dialog.setTitle("Edit Product");
			dialog.setHeaderText("ใส่ข้อมูลของสินค้า");
			ButtonType logButton = new ButtonType("Edit", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.CANCEL);

			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			grid.add(new Label("Product name:"), 0, 0);
			TextField name = new TextField();
			name.setText(lineItem.getProduct().getName());
			grid.add(name, 1, 0);
			grid.add(new Label("Price:"), 0, 1);
			TextField price = new TextField();
			price.setText(lineItem.getProduct().getPrice()+"");
			price.setMaxWidth(90);
			price.setPromptText("0.00");
			GridPane subgrid = new GridPane();
			subgrid.add(price, 0, 0);
			subgrid.add(new Label("  Baht."), 1, 0);
			grid.add(subgrid, 1, 1);
			grid.add(new Label("Quality:"), 0, 2);
			GridPane subgrid2 = new GridPane();
			TextField qty = new TextField();
			qty.setText(lineItem.getQuantity().get());
			qty.setMaxWidth(90);
			qty.setPromptText("0");
			subgrid2.add(qty, 0, 0);
			grid.add(subgrid2, 1, 2);

			dialog.setResultConverter(dialogButton -> {
				if (dialogButton==logButton) {
					lineItem.setQuantity(Integer.parseInt(qty.getText()));
					lineItem.getProduct().setName(name.getText());
					lineItem.getProduct().setPrice(Double.parseDouble(price.getText()));
					tableStock.refresh();
					database.openDatabase();
					database.editData(lineItem);
					database.closeDatabase();
					return 200;
				}

				return 500;

			});

			dialog.getDialogPane().setContent(grid);
			dialog.show();
		}

	}

	public void actionDelete(){
		LineItem lineItem = tableStock.getSelectionModel().getSelectedItem();
		if(lineItem != null){
			Dialog<Integer> dialog = new Dialog<>();
			dialog.setTitle("Delete Product");
			dialog.setHeaderText("");
			ButtonType logButton = new ButtonType("Ok", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.CANCEL);
			dialog.show();

			dialog.setResultConverter(dialogButton -> {
				if (dialogButton==logButton) {
					productCatalog.deleteItem(lineItem);
					database.openDatabase();
					database.Remove(lineItem);
					database.closeDatabase();
					setNewY();
					return 200;
				}

				return 500;

			});

		}

	}
	
	@FXML
	void actionAddStockBtn() {
		createAddStockDialog();
	}
	/**
	 * Create Dialog pop up after click add button.
	 */
    private void createAddStockDialog() {
    	Dialog<Integer> dialog = new Dialog<>();
    	dialog.setTitle("Add Product");
    	dialog.setHeaderText("ใส่ข้อมูลของสินค้า");
    	ButtonType logButton = new ButtonType("Add", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.CANCEL);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	grid.add(new Label("Product name:"), 0, 0);
    	TextField name = new TextField();
    	name.setPromptText("ชื่อสินค้า");
    	grid.add(name, 1, 0);
    	grid.add(new Label("Price:"), 0, 1);
    	TextField price = new TextField();
    	price.setMaxWidth(90);
    	price.setPromptText("0.00");
    	GridPane subgrid = new GridPane();
    	subgrid.add(price, 0, 0);
    	subgrid.add(new Label("  Baht."), 1, 0);
    	grid.add(subgrid, 1, 1);
    	grid.add(new Label("Quality:"), 0, 2);
    	GridPane subgrid2 = new GridPane();
    	TextField qty = new TextField();
    	qty.setMaxWidth(90);
    	qty.setPromptText("0");
    	subgrid2.add(qty, 0, 0);
    	grid.add(subgrid2, 1, 2);
    	
    	/**
    	 * Action after click Add button
    	 * @return 200 is OK, 505 is error.
    	 */
    	dialog.setResultConverter(dialogButton -> {
    		if (dialogButton==logButton) {
    			double p = Double.parseDouble(price.getText());
    			int q = Integer.parseInt(qty.getText());
    			addProduct(name.getText(), p, q);
    			return 200;
    		} else {return 505;}
    	});
    	dialog.getDialogPane().setContent(grid);
    	dialog.show();
    }
    
    private void addProduct(String name, double price, int qty) {
    	//call method "open database"
    	database.openDatabase();
    	
    	//call method "insert to database"
    	LineItem i = new LineItem(new Product(productCatalog.getLineItems().size()+1,name,price),qty);
    	productCatalog.addItem(i);
    	database.addData(i);
		setNewY();
    	//call method "close database"
		database.closeDatabase();
    }

    public void setNewY(){
		y = (productCatalog.getLineItems().size()*30+65);
	}

}
