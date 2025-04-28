package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Doctor;

public class DoctorSignup extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Sign Up - CuraLink");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField specializationField = new TextField();
        specializationField.setPromptText("Specialization");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button signupBtn = new Button("Sign Up");
        Button backBtn = new Button("Back");

        signupBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || specializationField.getText().isEmpty() || contactField.getText().isEmpty()
                    || emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields!").showAndWait();
                return;
            }

            Doctor newDoctor = new Doctor(
                    nameField.getText(),
                    specializationField.getText(),
                    contactField.getText(),
                    emailField.getText(),
                    passwordField.getText()
            );

            DataManager.saveDoctor(newDoctor);
            new Alert(Alert.AlertType.INFORMATION, "Sign Up Successful!").showAndWait();
            new DoctorLogin().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, nameField, specializationField, contactField, emailField, passwordField, signupBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30px;");

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
