package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/controllers/OnboardingScreen1.fxml"));
			Scene scene = new Scene(root, Color.WHITE);
			scene.getStylesheets().add(getClass().getResource("/resources/css/onboarding.css").toExternalForm());
			primaryStage.setScene(scene);
//			primaryStage.setFullScreen(true);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/icons/helpinghands_logo.png")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
