package application;

import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class ConfirmPageController {
    Preferences userPreferences = Preferences.userRoot();
    @FXML
    private Label buyButtonLabelText;

    @FXML
    private Button confirmButton;

    public void setBuyButtonLabelText(String text) {
        buyButtonLabelText.setText(text);
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) {

        Window owner = confirmButton.getScene().getWindow();
        if (userPreferences.getInt("userID", 0) == 0) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please Login to your account");
            return;
        }
        // Handle the confirmation action here
        System.out.println("Confirmed!");
        // You can close the stage or perform any other necessary actions
        // ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    public void initialize() {
        // Set initial size
        setButtonSize(75.0, 26.0);

        // Handle hover events
        confirmButton.setOnMouseEntered(event -> {
            setButtonSize(100.0, 40.0);
            confirmButton.setLayoutY(407 - 14);
            confirmButton.setLayoutX(170 - 15);
            confirmButton.setStyle("-fx-background-color: #FF0000");
        });

        confirmButton.setOnMouseExited(event -> {
            setButtonSize(75.0, 26.0);
            confirmButton.setLayoutY(407);
            confirmButton.setLayoutX(170);
            confirmButton.setStyle("-fx-background-color: #ffc107");
        });

        // Handle button action
        confirmButton.setOnAction(this::confirmButtonAction);
    }

    private void setButtonSize(double width, double height) {
        confirmButton.setMinWidth(width);
        confirmButton.setMinHeight(height);
        confirmButton.setMaxWidth(width);
        confirmButton.setMaxHeight(height);
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
