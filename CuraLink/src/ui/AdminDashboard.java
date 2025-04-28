package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SessionManager;

public class AdminDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Dashboard - CuraLink");

        Label title = new Label("Welcome, " + SessionManager.getLoggedStaff().getName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button manageStaffBtn = new Button("Manage Staff");
        Button manageBillingBtn = new Button("Manage Billing");
        Button manageEmergenciesBtn = new Button("Manage Emergencies");
        Button manageResourcesBtn = new Button("Manage Resources");
        Button logoutBtn = new Button("Logout");

        manageStaffBtn.setOnAction(e -> {
            new ManageStaffPage().start(new Stage());
            primaryStage.close();
        });

        manageBillingBtn.setOnAction(e -> {
            new ManageBillingPage().start(new Stage());
            primaryStage.close();
        });

        manageEmergenciesBtn.setOnAction(e -> {
            new ManageEmergenciesPage().start(new Stage());
            primaryStage.close();
        });

        manageResourcesBtn.setOnAction(e -> {
            new ManageResourcesPage().start(new Stage());
            primaryStage.close();
        });

        logoutBtn.setOnAction(e -> {
            SessionManager.logout();
            new HomePage().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, manageStaffBtn, manageBillingBtn, manageEmergenciesBtn, manageResourcesBtn, logoutBtn);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
