module HelpingHands {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.desktop;
	requires jbcrypt;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.fxml;
}
