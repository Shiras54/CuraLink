package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Staff;
import util.SessionManager;

import java.util.ArrayList;

public class AdminLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Login - CuraLink");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button signupBtn = new Button("Sign Up");
        Button backBtn = new Button("Back");

        loginBtn.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            ArrayList<Staff> staffList = DataManager.loadStaff();
            for (Staff staff : staffList) {
                if (staff.getEmail().equals(email) && staff.getPassword().equals(password) && staff.getRole().equals("admin")) {
                    SessionManager.setLoggedStaff(staff);
                    new AdminDashboard().start(new Stage());
                    primaryStage.close();
                    return;
                }
            }
            new Alert(Alert.AlertType.ERROR, "Invalid email or password!").showAndWait();
        });

        signupBtn.setOnAction(e -> {
            new AdminSignup().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, emailField, passwordField, loginBtn, signupBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30px;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
