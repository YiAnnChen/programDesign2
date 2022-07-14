module combineGame {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires java.management;
	requires dom4j;
	requires java.compiler;
	
	opens application to javafx.graphics, javafx.fxml;
}
