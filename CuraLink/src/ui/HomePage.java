package ui;
import controller.DataManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
    	DataManager.ensureDefaultAdmin();
        primaryStage.setTitle("CuraLink Health App");

        Label title = new Label("Welcome to CuraLink Health");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Patient Buttons
        Button patientLoginBtn = new Button("Patient Login");
        Button patientSignupBtn = new Button("Patient Signup");

        // Doctor Buttons
        Button doctorLoginBtn = new Button("Doctor Login");
        Button doctorSignupBtn = new Button("Doctor Signup");

        // Admin Buttons
        Button adminLoginBtn = new Button("Admin Login");
        Button adminSignupBtn = new Button("Admin Signup");

        patientLoginBtn.setPrefWidth(150);
        patientSignupBtn.setPrefWidth(150);
        doctorLoginBtn.setPrefWidth(150);
        doctorSignupBtn.setPrefWidth(150);
        adminLoginBtn.setPrefWidth(150);
        adminSignupBtn.setPrefWidth(150);

        patientLoginBtn.setOnAction(e -> {
            new PatientLogin().start(new Stage());
            primaryStage.close();
        });

        patientSignupBtn.setOnAction(e -> {
            new PatientSignup().start(new Stage());
            primaryStage.close();
        });

        doctorLoginBtn.setOnAction(e -> {
            new DoctorLogin().start(new Stage());
            primaryStage.close();
        });

        doctorSignupBtn.setOnAction(e -> {
            new DoctorSignup().start(new Stage());
            primaryStage.close();
        });

        adminLoginBtn.setOnAction(e -> {
            new AdminLogin().start(new Stage());
            primaryStage.close();
        });

        adminSignupBtn.setOnAction(e -> {
            new AdminSignup().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(30);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50px;");

        layout.getChildren().addAll(
            title,
            new HBox(10, patientLoginBtn, patientSignupBtn),
            new HBox(10, doctorLoginBtn, doctorSignupBtn),
            new HBox(10, adminLoginBtn, adminSignupBtn)
        );

        Scene scene = new Scene(layout, 500, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
