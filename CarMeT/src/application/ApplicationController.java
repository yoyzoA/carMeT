package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ApplicationController /* implements Initializable */ {

	Preferences userPreferences=Preferences.userRoot();
	

	private Stage stage;
	private Scene scene;
	public Parent root;
	@FXML
    Button Sell_Button;
	@FXML
    Button Purchases_Button;
	@FXML
	Button Login_Button;
	@FXML
	Button Signup_Button;


	public void Home(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("HomePage.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void SignOut(ActionEvent event) throws IOException, BackingStoreException {
		userPreferences.clear();
		infoBox("Logout Successful!", null, "Success");
	}


	public void Explore(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ExplorePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("ExplorePage.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();

	}


	public void Profile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void MyPurchases(ActionEvent event) throws IOException {
		Window owner = Sell_Button.getScene().getWindow();
		if(userPreferences.getInt("userID", 0)==0){
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please Login to your account");
                    return;
		}
		root = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("MyPurchases.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void SellPage(ActionEvent event) throws IOException {
		Window owner = Sell_Button.getScene().getWindow();
		if(userPreferences.getInt("userID", 0)==0){
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please Login to your account");
                    return;
		}
		root = FXMLLoader.load(getClass().getResource("SellPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void SellCarPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SellCarPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void SellPartPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SellPartPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void LogInPage(ActionEvent event) throws IOException {
		Window owner = Login_Button.getScene().getWindow();
		if(userPreferences.getInt("userID", 0)!=0){
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Already Logged in");
                    return;
		}
		root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void SignUpPage(ActionEvent event) throws IOException {
		Window owner = Signup_Button.getScene().getWindow();
		if(userPreferences.getInt("userID", 0)!=0){
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Already Logged in");
                    return;
		}
		root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void BuyPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("BuyPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void ReviewPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReviewPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
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

	public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}