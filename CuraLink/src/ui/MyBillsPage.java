package ui;

import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Billing;
import util.SessionManager;

import java.util.ArrayList;

public class MyBillsPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Bills - CuraLink");

        Label title = new Label("My Billing Information");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<String> billsList = new ListView<>();
        ArrayList<Billing> allBills = DataManager.loadBillings();
        String currentUsername = SessionManager.getLoggedPatient().getUsername();

        for (Billing bill : allBills) {
            if (bill.getPatientUsername().equals(currentUsername)) {
                String item = "Amount: $" + bill.getAmount() + " | Status: " + bill.getStatus();
                billsList.getItems().add(item);
            }
        }

        Button payButton = new Button("Pay Selected Bill");
        Button backButton = new Button("Back");

        payButton.setOnAction(e -> {
            int selectedIndex = billsList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Billing selectedBill = getUserBills().get(selectedIndex);
                if (selectedBill.getStatus().equals("Paid")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bill already paid.");
                    alert.showAndWait();
                    return;
                }

                selectedBill.markPaid("Credit Card", "", java.time.LocalDate.now().toString());
                DataManager.saveData(allBills, "billings.ser");

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment successful!");
                alert.showAndWait();

                new MyBillsPage().start(new Stage());
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a bill to pay.");
                alert.showAndWait();
            }
        });

        backButton.setOnAction(e -> {
            new PatientDashboard().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, billsList, payButton, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40px;");

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Billing> getUserBills() {
        ArrayList<Billing> result = new ArrayList<>();
        ArrayList<Billing> allBills = DataManager.loadBillings();
        String currentUsername = SessionManager.getLoggedPatient().getUsername();
        for (Billing bill : allBills) {
            if (bill.getPatientUsername().equals(currentUsername)) {
                result.add(bill);
            }
        }
        return result;
    }
}
