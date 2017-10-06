import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApp extends Application {
	public void start(Stage primaryStage) {
		MainController mc = new MainController(primaryStage);
		mc.startApplication();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
