package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                Preferences userPreferences=Preferences.userRoot();
                if (email_textfield.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter your email");
                    return;
                }
                if(!isValidEmail(email_textfield.getText())){
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter a valid email");
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
                String username=user.getUsername();
                System.out.println("please this " + userID);
                Thread.sleep(1000);

                if (!flag) {
                    infoBox("Please enter correct Email and Password", null, "Failed");
                } else {
                    infoBox("Login Successful!", null, "Success");
                    userPreferences.putInt("userID", userID);
                    userPreferences.put("username", username);
                    
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

    private final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";

    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
