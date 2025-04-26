package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Appointment;
import model.Doctor;
import util.SessionManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookAppointment extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Appointment - CuraLink");

        Label title = new Label("Book Appointment");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> doctorComboBox = new ComboBox<>();
        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("Time (e.g., 10:00)");

        TextArea notesField = new TextArea();
        notesField.setPromptText("Notes...");
        notesField.setPrefRowCount(3);

        Button bookBtn = new Button("Book Appointment");
        Button backBtn = new Button("Back");

        ArrayList<Doctor> doctors = DataManager.loadDoctors();
        for (Doctor doc : doctors) {
            doctorComboBox.getItems().add(doc.getUsername() + " - " + doc.getName());
        }

        bookBtn.setOnAction(e -> {
            String doctorSelection = doctorComboBox.getValue();
            LocalDate date = datePicker.getValue();
            String time = timeField.getText();
            String notes = notesField.getText();

            if (doctorSelection == null || date == null || time.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all required fields.");
                alert.showAndWait();
                return;
            }

            String doctorUsername = doctorSelection.split(" - ")[0];
            Appointment appt = new Appointment(
                    SessionManager.getLoggedPatient().getUsername(),
                    doctorUsername,
                    date.toString(),
                    time,
                    notes,
                    ""
            );

            ArrayList<Appointment> appointments = DataManager.loadAppointments();
            appointments.add(appt);
            DataManager.saveData(appointments, "appointments.ser");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment booked successfully!");
            alert.showAndWait();

            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        backBtn.setOnAction(e -> {
            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, doctorComboBox, datePicker, timeField, notesField, bookBtn, backBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
