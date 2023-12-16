package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    	public void HomeSignUp(ActionEvent event) throws IOException {
		try {
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
}
