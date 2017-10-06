/**
 * Sample Skeleton for 'StartPage.fxml' Controller Class
 */

package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartViewController {
	@FXML protected AnchorPane rootPane;
	@FXML protected Button userBtn, adminBtn;
	
	@FXML
	public void actionUserBtn() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SalePage.fxml"));
	        Parent root = loader.load();
	        Stage stage = new Stage();
	        Scene scene = new Scene(root, 1280, 720);
			stage.setTitle("POS project");
			scene.getStylesheets().add(getClass().getResource("/views/salePage.css").toExternalForm());
			//stage.setFullScreen(true);
			stage.setScene(scene);
			stage.show();
			
			closeOldwindow();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionAdminBtn() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));
		rootPane.getChildren().setAll(pane);
	}
	
	public void closeOldwindow() {
		Stage stage = (Stage) userBtn.getScene().getWindow();
		stage.close();
	}
	
}
