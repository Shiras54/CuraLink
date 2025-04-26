package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Dashboard - CuraLink");

        Label title = new Label("Patient Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button bookAppointmentBtn = new Button("Book Appointment");
        Button myAppointmentsBtn = new Button("My Appointments");
        Button emergencyBtn = new Button("Request Emergency Help");
        Button billingBtn = new Button("View My Bills");
        Button logoutBtn = new Button("Logout");

        bookAppointmentBtn.setPrefWidth(250);
        myAppointmentsBtn.setPrefWidth(250);
        emergencyBtn.setPrefWidth(250);
        billingBtn.setPrefWidth(250);
        logoutBtn.setPrefWidth(250);

        bookAppointmentBtn.setOnAction(e -> {
            // To be linked to BookAppointment screen
        });

        myAppointmentsBtn.setOnAction(e -> {
            // To be linked to MyAppointments screen
        });

        emergencyBtn.setOnAction(e -> {
            // To be linked to EmergencyRequest screen
        });

        billingBtn.setOnAction(e -> {
            // To be linked to MyBillings screen
        });

        logoutBtn.setOnAction(e -> {
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, bookAppointmentBtn, myAppointmentsBtn, emergencyBtn, billingBtn, logoutBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50px;");

        Scene scene = new Scene(layout, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
