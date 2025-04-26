package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Billing;

import java.util.ArrayList;

public class ManageBillingPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Billing - CuraLink");

        Label title = new Label("Billing Management");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> billingList = new ListView<>();
        ArrayList<Billing> allBillings = DataManager.loadBillings();

        for (Billing b : allBillings) {
            String item = "Patient: " + b.getPatientUsername() +
                          " | Amount: $" + b.getAmount() +
                          " | Status: " + b.getStatus();
            billingList.getItems().add(item);
        }

        Button createInvoiceButton = new Button("Create New Invoice");
        Button backButton = new Button("Back");

        createInvoiceButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Invoice");
            dialog.setHeaderText("Enter Patient Username:");
            dialog.setContentText("Username:");

            dialog.showAndWait().ifPresent(username -> {
                TextInputDialog amountDialog = new TextInputDialog();
                amountDialog.setTitle("New Invoice");
                amountDialog.setHeaderText("Enter Amount:");
                amountDialog.setContentText("Amount:");

                amountDialog.showAndWait().ifPresent(amountStr -> {
                    try {
                        double amount = Double.parseDouble(amountStr);
                        Billing newBill = new Billing(username, amount, java.time.LocalDate.now().toString());
                        allBillings.add(newBill);
                        DataManager.saveData(allBillings, "billings.ser");

                        new ManageBillingPage().start(new Stage());
                        primaryStage.close();
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid amount input.");
                        alert.showAndWait();
                    }
                });
            });
        });

        backButton.setOnAction(e -> {
            new AdminDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, billingList, createInvoiceButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
