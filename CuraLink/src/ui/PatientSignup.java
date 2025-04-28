package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Patient;

public class PatientSignup extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Sign Up - CuraLink");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField dobField = new TextField();
        dobField.setPromptText("Date of Birth (dd/mm/yyyy)");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button signupBtn = new Button("Sign Up");
        Button backBtn = new Button("Back");

        signupBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || dobField.getText().isEmpty() || contactField.getText().isEmpty()
                    || emailField.getText().isEmpty() || addressField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields!").showAndWait();
                return;
            }

            Patient newPatient = new Patient(
                    nameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    nameField.getText(),
                    dobField.getText(),
                    contactField.getText(),                    
                    addressField.getText()                    
            );

            DataManager.savePatient(newPatient);
            new Alert(Alert.AlertType.INFORMATION, "Sign Up Successful!").showAndWait();
            new PatientLogin().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, nameField, dobField, contactField, emailField, addressField, passwordField, signupBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30px;");

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
