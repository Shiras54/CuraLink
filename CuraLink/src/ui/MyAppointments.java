package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Appointment;
import util.SessionManager;

import java.util.ArrayList;

public class MyAppointments extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Appointments - CuraLink");

        Label title = new Label("My Appointments");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> appointmentsList = new ListView<>();

        ArrayList<Appointment> allAppointments = DataManager.loadAppointments();
        String currentUsername = SessionManager.getLoggedPatient().getUsername();

        for (Appointment appt : allAppointments) {
            if (appt.getPatientUsername().equals(currentUsername)) {
                String item = "Doctor: " + appt.getDoctorUsername() +
                              " | Date: " + appt.getDate() +
                              " | Time: " + appt.getTime();
                appointmentsList.getItems().add(item);
            }
        }

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, appointmentsList, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
