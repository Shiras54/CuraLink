package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmergencyRequest;

import java.util.ArrayList;

public class ManageEmergenciesPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Emergency Requests - CuraLink");

        Label title = new Label("Emergency Requests");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> requestList = new ListView<>();
        ArrayList<EmergencyRequest> allRequests = DataManager.loadEmergencyRequests();

        for (EmergencyRequest r : allRequests) {
            String item = "Patient: " + r.getPatientUsername() +
                          " | Severity: " + r.getSeverity() +
                          " | Status: " + r.getStatus();
            requestList.getItems().add(item);
        }

        Button acceptButton = new Button("Accept");
        Button rejectButton = new Button("Reject");
        Button backButton = new Button("Back");

        acceptButton.setOnAction(e -> {
            int selectedIndex = requestList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                EmergencyRequest selected = allRequests.get(selectedIndex);
                selected.setStatus("Accepted");
                DataManager.saveData(allRequests, "emergencies.ser");
                new ManageEmergenciesPage().start(new Stage());
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Select a request to accept.");
                alert.showAndWait();
            }
        });

        rejectButton.setOnAction(e -> {
            int selectedIndex = requestList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                EmergencyRequest selected = allRequests.get(selectedIndex);
                selected.setStatus("Rejected");
                DataManager.saveData(allRequests, "emergencies.ser");
                new ManageEmergenciesPage().start(new Stage());
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Select a request to reject.");
                alert.showAndWait();
            }
        });

        backButton.setOnAction(e -> {
            new AdminDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, requestList, acceptButton, rejectButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
