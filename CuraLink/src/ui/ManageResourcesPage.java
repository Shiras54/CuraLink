package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Resource;

import java.util.ArrayList;

public class ManageResourcesPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Resources - CuraLink");

        Label title = new Label("Resource Management");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> resourceList = new ListView<>();
        ArrayList<Resource> allResources = DataManager.loadResources();

        for (Resource r : allResources) {
            String item = "Name: " + r.getName() +
                          " | Type: " + r.getType() +
                          " | Status: " + r.getStatus();
            resourceList.getItems().add(item);
        }

        Button addResourceButton = new Button("Add New Resource");
        Button backButton = new Button("Back");

        addResourceButton.setOnAction(e -> {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("New Resource");
            nameDialog.setHeaderText("Enter Resource Name:");
            nameDialog.setContentText("Name:");

            nameDialog.showAndWait().ifPresent(name -> {
                ChoiceDialog<String> typeDialog = new ChoiceDialog<>("Room", "Room", "Equipment", "Ambulance");
                typeDialog.setTitle("New Resource");
                typeDialog.setHeaderText("Select Resource Type:");

                typeDialog.showAndWait().ifPresent(type -> {
                    Resource newRes = new Resource(name, type, "Available");
                    allResources.add(newRes);
                    DataManager.saveData(allResources, "resources.ser");

                    new ManageResourcesPage().start(new Stage());
                    primaryStage.close();
                });
            });
        });

        backButton.setOnAction(e -> {
            new AdminDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, resourceList, addResourceButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
