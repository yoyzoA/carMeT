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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignInController {

    private Stage stage;
    private Scene scene;
    public Parent root;
    int userID;
    // sign in page
    @FXML
    TextField email_textfield;
    @FXML
    PasswordField password_passwordfield;
    @FXML
    Button submitButton;

    public void HomeSignedIn(ActionEvent event) throws SQLException, IOException {
        
            try {
                Window owner = submitButton.getScene().getWindow();
                User user = new User();
                if (email_textfield.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter your email id");
                    return;
                }
                if (password_passwordfield.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter a password");
                    return;
                }

                String email = email_textfield.getText();
                String password = password_passwordfield.getText();
                boolean flag = user.userSignIn(password, email);
                userID = user.getuserID();
                System.out.println("please this " + userID);
                Thread.sleep(1000);

                if (!flag) {
                    infoBox("Please enter correct Email and Password", null, "Failed");
                } else {
                    infoBox("Login Successful!", null, "Success");
                    Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    String css = this.getClass().getResource("application.css").toExternalForm();
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
