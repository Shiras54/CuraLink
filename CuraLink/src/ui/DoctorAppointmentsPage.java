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

public class DoctorAppointmentsPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Appointments - CuraLink");

        Label title = new Label("Appointments for Dr. " + SessionManager.getLoggedStaff().getName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> appointmentsList = new ListView<>();
        ArrayList<Appointment> allAppointments = DataManager.loadAppointments();
        String doctorUsername = SessionManager.getLoggedStaff().getUsername();

        for (Appointment appt : allAppointments) {
            if (appt.getDoctorUsername().equals(doctorUsername)) {
                String item = "Patient: " + appt.getPatientUsername() +
                              " | Date: " + appt.getDate() +
                              " | Time: " + appt.getTime();
                appointmentsList.getItems().add(item);
            }
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            new DoctorDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, appointmentsList, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
