package ui;


import controller.DataManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new HomePage().start(primaryStage);
    }

    public static void main(String[] args) {
    	DataManager.ensureDefaultAdmin();
        launch(args);
    }
}
