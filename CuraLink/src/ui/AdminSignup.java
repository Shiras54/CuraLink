package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Staff;

public class AdminSignup extends Application {

    private static final String SECRET_ADMIN_CODE = "CURALINK123";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Sign Up - CuraLink");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField codeField = new TextField();
        codeField.setPromptText("Enter Admin Code");

        Button signupBtn = new Button("Sign Up");
        Button backBtn = new Button("Back");

        signupBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || contactField.getText().isEmpty()
                    || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || codeField.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields!").showAndWait();
                return;
            }

            if (!codeField.getText().equals(SECRET_ADMIN_CODE)) {
                new Alert(Alert.AlertType.ERROR, "Incorrect Admin Code!").showAndWait();
                return;
            }

            Staff newAdmin = new Staff(
                    nameField.getText(),
                    contactField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    "admin"
            );

            DataManager.saveStaff(newAdmin);
            new Alert(Alert.AlertType.INFORMATION, "Sign Up Successful!").showAndWait();
            new AdminLogin().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, nameField, contactField, emailField, passwordField, codeField, signupBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30px;");

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
