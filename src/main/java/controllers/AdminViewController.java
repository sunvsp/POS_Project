/**
 * Sample Skeleton for 'AdminPage.fxml' Controller Class
 */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminViewController {

    @FXML private Button manageBtn = new Button();
    @FXML private Button orderHistoryBtn = new Button();
    @FXML private Button statBtn = new Button();
    @FXML private Button settingBtn = new Button();
    @FXML private Button logoutBtn = new Button();
    
    @FXML
    private AnchorPane adminPane;

    @FXML
    void actionManageBtn(ActionEvent event) throws IOException {
    	BorderPane pane = FXMLLoader.load(getClass().getResource("/views/StockPage.fxml"));
    	pane.setStyle("-fx-background-color: #fffafa;");
    	pane.getStylesheets().add(getClass().getResource("/views/stock.css").toExternalForm());
    	adminPane.getChildren().setAll(pane);
    	outstanding(0);
    }
    
    @FXML
    void actionOrderHistoryBtn() throws IOException {
    	BorderPane pane = FXMLLoader.load(getClass().getResource("/views/OrderHistoryPage.fxml"));
    	pane.setStyle("-fx-background-color: #fffafa;");
    	adminPane.getChildren().setAll(pane);
    	outstanding(1);
    }
    
    @FXML
    void actionStatBtn() throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/StatPage.fxml"));
    	pane.setStyle("-fx-background-color: #fffafa;");
    	adminPane.getChildren().setAll(pane);
    	outstanding(2);
    }

    @FXML
    void actionSettingBtn(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/SettingPage.fxml"));
    	pane.setStyle("-fx-background-color: #fffafa;");
    	adminPane.getChildren().setAll(pane);
    	outstanding(3);
    }

    @FXML
    void actionLogoutBtn(ActionEvent event) throws IOException {
//    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/LogoutPage.fxml"));
//    	pane.setStyle("-fx-background-color: #fffafa;");
//    	adminPane.getChildren().setAll(pane);
    	Dialog<Integer> dialog = new Dialog<>();
    	dialog.setTitle("Logout");
    	dialog.setHeaderText("Do you want to logout.");
    	ButtonType logButton = new ButtonType("Yes", ButtonData.YES);
    	dialog.getDialogPane().getButtonTypes().addAll(logButton, ButtonType.NO);
    	
    	dialog.setResultConverter(dialogButton -> {
    		if (dialogButton==logButton) {
    			createStartMenu();
    			return 200;
    		} else {return 505;}
    	});
    	dialog.show();
    	outstanding(4);
    }
//    @FXML
//    void bc5151(ActionEvent event) {
//
//    }
    
    private void createStartMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StartPage.fxml"));
	        Parent root = loader.load();
	        Stage stage = new Stage();
	        Scene scene = new Scene(root, 500, 500);
			stage.setTitle("POS project");
			scene.getStylesheets().add(getClass().getResource("/views/salePage.css").toExternalForm());
			//stage.setFullScreen(true);
			stage.setScene(scene);
			stage.show();
			
			Stage oldStage = (Stage) adminPane.getScene().getWindow();
			oldStage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * Set opacity of any button.
     * @param n is number sequence of button.
     * 0 is manageBtn
     * 1 is OrderHistoryBtn
     */
    private void outstanding(int n) {
    	if (n==0) {
    		manageBtn.setOpacity(1);
    		manageBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 2);"
    				+ "-fx-background-color: #fffafa;");
    		orderHistoryBtn.setStyle("-fx-background-color: #fffafa;");
    		statBtn.setStyle("-fx-background-color: #fffafa;");
    		settingBtn.setStyle("-fx-background-color: #fffafa;");
    		logoutBtn.setStyle("-fx-background-color: #fffafa;");
    	}
    	else {manageBtn.setOpacity(0.5);}
    	if (n==1) {
    		orderHistoryBtn.setOpacity(1);
    		orderHistoryBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 7, 0, 2, 2);"
    				+ "-fx-background-color: #fffafa;");
    		manageBtn.setStyle("-fx-background-color: #fffafa;");
    		statBtn.setStyle("-fx-background-color: #fffafa;");
    		settingBtn.setStyle("-fx-background-color: #fffafa;");
    		logoutBtn.setStyle("-fx-background-color: #fffafa;");
    	}
    	else {orderHistoryBtn.setOpacity(0.5);}
    	if (n==2) {
    		statBtn.setOpacity(1);
    		statBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 2);"
    				+ "-fx-background-color: #fffafa;");
    		manageBtn.setStyle("-fx-background-color: #fffafa;");
    		orderHistoryBtn.setStyle("-fx-background-color: #fffafa;");
    		settingBtn.setStyle("-fx-background-color: #fffafa;");
    		logoutBtn.setStyle("-fx-background-color: #fffafa;");
    	}
    	else {statBtn.setOpacity(0.5);}
    	if (n==3) {
    		settingBtn.setOpacity(1);
    		settingBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 2);"
    				+ "-fx-background-color: #fffafa;");
    		manageBtn.setStyle("-fx-background-color: #fffafa;");
    		orderHistoryBtn.setStyle("-fx-background-color: #fffafa;");
    		statBtn.setStyle("-fx-background-color: #fffafa;");
    		logoutBtn.setStyle("-fx-background-color: #fffafa;");
    	}
    	else {settingBtn.setOpacity(0.5);}
    	if (n==4) {
    		logoutBtn.setOpacity(1);
    		logoutBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 2);"
    				+ "-fx-background-color: #fffafa;");
    		manageBtn.setStyle("-fx-background-color: #fffafa;");
    		orderHistoryBtn.setStyle("-fx-background-color: #fffafa;");
    		statBtn.setStyle("-fx-background-color: #fffafa;");
    		settingBtn.setStyle("-fx-background-color: #fffafa;");
    	}
    	else {logoutBtn.setOpacity(0.5);}
    }
}
