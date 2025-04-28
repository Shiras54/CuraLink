package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DoctorAvailability;
import util.SessionManager;

import java.util.ArrayList;

public class SetAvailabilityPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Set Availability - CuraLink");

        Label title = new Label("Set Your Availability");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> weekdayBox = new ComboBox<>();
        weekdayBox.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        weekdayBox.setPromptText("Select Day");

        TextField startTimeField = new TextField();
        startTimeField.setPromptText("Start Time (e.g. 09:00)");

        TextField endTimeField = new TextField();
        endTimeField.setPromptText("End Time (e.g. 17:00)");

        Button saveButton = new Button("Save Availability");
        Button backButton = new Button("Back");

        saveButton.setOnAction(e -> {
            String weekday = weekdayBox.getValue();
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();

            if (weekday == null || startTime.isEmpty() || endTime.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please complete all fields.");
                alert.showAndWait();
                return;
            }

            DoctorAvailability availability = new DoctorAvailability(
                    SessionManager.getLoggedDoctor().getUsername(),
                    weekday,
                    startTime,
                    endTime
            );

            ArrayList<DoctorAvailability> availabilities = DataManager.loadDoctorAvailabilities();
            availabilities.add(availability);
            DataManager.saveData(availabilities, "availabilities.ser");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Availability saved!");
            alert.showAndWait();

            new DoctorDashboard().start(new Stage());
            primaryStage.close();
        });

        backButton.setOnAction(e -> {
            new DoctorDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, weekdayBox, startTimeField, endTimeField, saveButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
