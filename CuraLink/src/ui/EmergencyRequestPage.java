package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmergencyRequest;
import util.SessionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmergencyRequestPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Emergency Request - CuraLink");

        Label title = new Label("Request Emergency Assistance");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField locationField = new TextField();
        locationField.setPromptText("Your Location...");

        ComboBox<String> severityBox = new ComboBox<>();
        severityBox.getItems().addAll("Critical", "High", "Moderate", "Low");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Short Description...");
        descriptionArea.setPrefRowCount(3);

        Button submitBtn = new Button("Submit Request");
        Button backBtn = new Button("Back");

        submitBtn.setOnAction(e -> {
            String location = locationField.getText();
            String severity = severityBox.getValue();
            String description = descriptionArea.getText();

            if (location.isEmpty() || severity == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill location and severity.");
                alert.showAndWait();
                return;
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            EmergencyRequest request = new EmergencyRequest(
                    SessionManager.getLoggedPatient().getUsername(),
                    location,
                    severity,
                    description,
                    timestamp
            );

            ArrayList<EmergencyRequest> requests = DataManager.loadEmergencyRequests();
            requests.add(request);
            DataManager.saveData(requests, "emergencies.ser");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Emergency Request Sent!");
            alert.showAndWait();

            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, locationField, severityBox, descriptionArea, submitBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
