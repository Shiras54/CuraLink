/**
 * 
 */
/**
 * 
 */
module CuraLink {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	
	opens ui to javafx.graphics, javafx.fxml;
}