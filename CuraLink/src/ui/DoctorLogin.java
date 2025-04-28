package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Doctor;
import util.SessionManager;

import java.util.ArrayList;

public class DoctorLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Login - CuraLink");

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

            ArrayList<Doctor> doctors = DataManager.loadDoctors();
            for (Doctor doctor : doctors) {
                if (doctor.getEmail().equals(email) && doctor.getPassword().equals(password)) {
                    SessionManager.setLoggedStaff(doctor);
                    new DoctorDashboard().start(new Stage());
                    primaryStage.close();
                    return;
                }
            }
            new Alert(Alert.AlertType.ERROR, "Invalid email or password!").showAndWait();
        });

        signupBtn.setOnAction(e -> {
            new DoctorSignup().start(new Stage());
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
