package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SessionManager;
import model.Patient;
import controller.DataManager;
import java.util.ArrayList;

public class PatientLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Login - CuraLink");

        Label title = new Label("Patient Login");
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

            ArrayList<Patient> patients = DataManager.loadPatients();

            boolean success = false;
            for (Patient p : patients) {
                if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                    SessionManager.setLoggedPatient(p);
                    success = true;
                    break;
                }
            }

            if (success) {
                new PatientDashboard().start(new Stage());
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
