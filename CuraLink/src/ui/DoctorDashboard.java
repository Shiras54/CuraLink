package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SessionManager;

public class DoctorDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Dashboard - CuraLink");

        Label title = new Label("Welcome, Dr. " + SessionManager.getLoggedDoctor().getName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button viewAppointmentsBtn = new Button("View My Appointments");
        Button setAvailabilityBtn = new Button("Set Availability");
        Button logoutBtn = new Button("Logout");

        viewAppointmentsBtn.setOnAction(e -> {
            new DoctorAppointmentsPage().start(new Stage());
            primaryStage.close();
        });

        setAvailabilityBtn.setOnAction(e -> {
            new SetAvailabilityPage().start(new Stage());
            primaryStage.close();
        });

        logoutBtn.setOnAction(e -> {
            SessionManager.logout();
            new LoginPage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, viewAppointmentsBtn, setAvailabilityBtn, logoutBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }}
