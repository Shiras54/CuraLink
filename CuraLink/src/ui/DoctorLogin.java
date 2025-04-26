package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SessionManager;
import model.Doctor;
import controller.DataManager;
import java.util.ArrayList;

public class DoctorLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Login - CuraLink");

        Label title = new Label("Doctor Login");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        loginBtn.setPrefWidth(200);
        backBtn.setPrefWidth(200);

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            ArrayList<Doctor> doctors = DataManager.loadDoctors();

            boolean success = false;
            for (Doctor d : doctors) {
                if (d.getUsername().equals(username) && d.getPassword().equals(password)) {
                    SessionManager.setLoggedDoctor(d);
                    success = true;
                    break;
                }
            }

            if (success) {
                new DoctorDashboard().start(new Stage());
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Login Failed");
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, usernameField, passwordField, loginBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50px;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
