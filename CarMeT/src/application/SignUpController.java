package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpController {

    private Stage stage;
    private Scene scene;
    public Parent root;

    @FXML
    TextField usernameSignup_textfield;
    @FXML
    TextField phonenumberSignup_textfield;
    @FXML
    TextField emailSignup_textfield;
    @FXML
    PasswordField passwordSignup_passwordfield;
    @FXML
    Button next_button;

    public void HomeSignUp(ActionEvent event) throws IOException,SQLException {
        try {
            Window owner = next_button.getScene().getWindow();

            if (usernameSignup_textfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your username");
            return;
        }

        if (emailSignup_textfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email");
            return;
        }
        if (passwordSignup_passwordfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        if (phonenumberSignup_textfield.getText().isEmpty() || !phonenumberSignup_textfield.getText().matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a phone Number");
            return;
        }

            String username = usernameSignup_textfield.getText();
            String email = emailSignup_textfield.getText();
            String password = passwordSignup_passwordfield.getText();
            String phoneNumber = phonenumberSignup_textfield.getText();
            Thread.sleep(1000);
            User user = new User(username, email, password, phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("HomePage.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void Home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("HomePage.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
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
