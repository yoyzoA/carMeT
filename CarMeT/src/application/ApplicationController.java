package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationController {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
 public void SignUpContinue(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("SignUpContinuePage.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  String css = this.getClass().getResource("application.css").toExternalForm();
  scene.getStylesheets().add(css);
  stage.setScene(scene);
  stage.show();
 }
 
 public void Home(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	 }
 
 public void Explore(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("ExplorePage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  scene.getStylesheets().add(css);
	  stage.setScene(scene);
	  stage.show();
	 }
 public void Profile(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();

	 }
 public void MyPurchases(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	 }
 
 public void SellPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("SellPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	  
	 }
 public void SellCarPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("SellCarPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	  
	 }
 public void SellPartPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("SellPartPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	  
	 }
 
 public void LogInPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	 }
 
 public void SignUpPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  scene.getStylesheets().add(css);
	  stage.setScene(scene);
	  stage.show();
	 }
 
 public void BuyPage(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("BuyPage.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	  stage.setScene(scene);
	  stage.show();
	 }
	}