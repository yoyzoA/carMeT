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
	public static int userID = 0;


	public void Home(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("HomePage.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void SignOut(ActionEvent event) throws IOException {
		userID = 0;
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
		root = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("MyPurchases.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	public void SellPage(ActionEvent event) throws IOException {
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
		root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void SignUpPage(ActionEvent event) throws IOException {
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

}