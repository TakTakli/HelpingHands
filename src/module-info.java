module HelpingHands {
	
		requires javafx.controls;
		requires javafx.graphics;
		requires javafx.fxml;
		requires java.desktop;
		requires java.sql;
		requires mysql.connector.j;
		requires javafx.base;
		
		opens application to javafx.graphics, javafx.fxml;
	

}
