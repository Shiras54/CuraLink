package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Staff;

import java.util.ArrayList;

public class ManageStaffPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Staff - CuraLink");

        Label title = new Label("Manage Staff Members");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> staffList = new ListView<>();
        ArrayList<Staff> allStaff = DataManager.loadStaff();

        for (Staff s : allStaff) {
            String item = "Username: " + s.getUsername() + " | Role: " + s.getRole();
            staffList.getItems().add(item);
        }

        Button deleteButton = new Button("Delete Selected Staff");
        Button backButton = new Button("Back");

        deleteButton.setOnAction(e -> {
            int selectedIndex = staffList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                allStaff.remove(selectedIndex);
                DataManager.saveData(allStaff, "staff.ser");
                new ManageStaffPage().start(new Stage());
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a staff member to delete.");
                alert.showAndWait();
            }
        });

        backButton.setOnAction(e -> {
            new AdminDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, staffList, deleteButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
