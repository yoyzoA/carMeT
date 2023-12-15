package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ApplicationController /* implements Initializable */ {

	private Stage stage;
	private Scene scene;
	public Parent root;
	int userID = 0;

	// values of the signup page
	@FXML
	TextField usernameSignup_textfield;
	@FXML
	TextField phonenumberSignup_textfield;
	@FXML
	TextField emailSignup_textfield;
	@FXML
	PasswordField passwordSignup_passwordfield;

	// sign in page
	@FXML
	TextField username_textfield;
	@FXML
	TextField email_textfield;
	@FXML
	PasswordField password_passwordfield;

	public void SignUpContinue(ActionEvent event) throws IOException {
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
		{
		}
		root = FXMLLoader.load(getClass().getResource("SignUpContinuePage.fxml"));
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
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();
	}

	public void HomeSignedIn(ActionEvent event) throws IOException {
		while (true) {
			try {
				User user = new User();

				String username = username_textfield.getText();
				String email = email_textfield.getText();
				String password = password_passwordfield.getText();
				user.userSignIn(username, password, email);
				Thread.sleep(1000);
				if (user.userID == 0) {
					return;
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			{
			}
		}
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();
	}

	public void Explore(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ExplorePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void Profile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();

	}

	public void MyPurchases(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();
	}

	public void SellPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SellPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();

	}

	public void SellCarPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SellCarPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();

	}

	public void SellPartPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SellPartPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();

	}

	public void LogInPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();
	}

	public void SignUpPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void BuyPage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BuyPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		stage.setScene(scene);
		stage.show();
	}
}
