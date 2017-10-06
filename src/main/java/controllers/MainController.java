package controllers;

import database.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ProductCatalog;

public class MainController {
	private Stage primaryStage;
	private ProductCatalog productCatalog;
	private Database database;
	
	public MainController(Stage primaryStage) {
		this.primaryStage = primaryStage;
		database = new Database();
	}
	
	public void startApplication() {
		display();
		createProductCatalog();

	}
	
	private void display() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StartPage.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root,500,500);
			primaryStage.setTitle("POS project");
//			scene.getStylesheets().add(getClass().getResource("/views/salePage.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void createProductCatalog(){
		productCatalog = ProductCatalog.getInstacnce();
		database.openDatabase();
		productCatalog.setLineItem(database.readFile());
		database.closeDatabase();
	}
}
