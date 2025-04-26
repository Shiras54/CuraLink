package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CuraLink Health App");

        Label title = new Label("Welcome to CuraLink Health");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button patientLoginBtn = new Button("Patient Login");
        Button doctorLoginBtn = new Button("Doctor Login");
        Button adminLoginBtn = new Button("Admin Login");

        patientLoginBtn.setPrefWidth(200);
        doctorLoginBtn.setPrefWidth(200);
        adminLoginBtn.setPrefWidth(200);

        patientLoginBtn.setOnAction(e -> {
            PatientLogin login = new PatientLogin();
            login.start(new Stage());
            primaryStage.close();
        });

        doctorLoginBtn.setOnAction(e -> {
            DoctorLogin login = new DoctorLogin();
            login.start(new Stage());
            primaryStage.close();
        });

        adminLoginBtn.setOnAction(e -> {
            AdminLogin login = new AdminLogin();
            login.start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, patientLoginBtn, doctorLoginBtn, adminLoginBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50px;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
