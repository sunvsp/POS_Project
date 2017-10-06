package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginViewController {
	@FXML
	private AnchorPane loginPane;
	
    @FXML
    private Button loginBtn;

    @FXML
    private Button cancelBtnAdminPage;

    @FXML
    void actionCancelBtnAdminPage(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/StartPage.fxml"));
    	loginPane.getChildren().setAll(pane);
    }

    @FXML
    void actionLoginBtn(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminPage.fxml"));
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
    
	public void closeOldwindow() {
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		stage.close();
	}
}
