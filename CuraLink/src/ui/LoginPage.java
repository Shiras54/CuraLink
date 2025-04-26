package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Selection");

        Button adminLoginButton = new Button("Admin Login");
        Button doctorLoginButton = new Button("Doctor Login");
        Button patientLoginButton = new Button("Patient Login");

        adminLoginButton.setOnAction(e -> {
            AdminLogin adminLogin = new AdminLogin();
            try {
                adminLogin.start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        doctorLoginButton.setOnAction(e -> {
            DoctorLogin doctorLogin = new DoctorLogin();
            try {
                doctorLogin.start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        patientLoginButton.setOnAction(e -> {
            PatientLogin patientLogin = new PatientLogin();
            try {
                patientLogin.start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(adminLoginButton, doctorLoginButton, patientLoginButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
