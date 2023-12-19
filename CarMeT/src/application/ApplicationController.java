package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.*;
import java.sql.*;

public class ApplicationController /* implements Initializable */ {

	Preferences userPreferences = Preferences.userRoot();

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
	@FXML
	private HBox partPurchasesHBox;
	@FXML
	private HBox carPurchasesHBox;

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
		// Window owner = Sell_Button.getScene().getWindow();
		// if (userPreferences.getInt("userID", 0) == 0) {
		// showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
		// "Please Login to your account");
		// return;
		// }

		carPurchasesHBox.getChildren().clear();
		String url = "jdbc:mysql://localhost:3306/carMeT";
		String username0 = "root";
		String password = "root";
		int customerID = userPreferences.getInt("userID", 0);

		// attributes of carOrder

		int orderID = 0;
		ArrayList<Integer> orderIDList = new ArrayList<>();
		String supplierName = "";
		ArrayList<String> supplierNameList = new ArrayList<>();
		int carID = 0;
		ArrayList<Integer> carIDList = new ArrayList<>();
		int price = 0;
		ArrayList<Integer> priceList = new ArrayList<>();

		// attributes of part

		String partName = "";
		ArrayList<String> partNameList = new ArrayList<>();
		String partDesc = "";
		ArrayList<String> partDescList = new ArrayList<>();
		double partPrice = 0;
		ArrayList<Double> partPriceList = new ArrayList<>();
		String supplierNameP = "";
		ArrayList<String> supplierNameListP = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(url, username0, password);
			String sql = "SELECT po.orderID, po.partID, p.name,p.Description,p.price,u.userName from part p , user u, partorder po , orders o where po.orderID = o.orderID and p.partID=po.partID and u.userID=po.supplierID and po.customerID=\""
					+ customerID + "\"";
			preparedStatement = connection.prepareStatement(sql);

			// Execute the query and get the result set
			resultSet = preparedStatement.executeQuery();

			// Process the result set
			while (resultSet.next()) {
				// Retrieve values from the result set
				orderID = Integer.parseInt(resultSet.getString("orderID"));
				orderIDList.add(carID);

				partName = resultSet.getString("name");
				partNameList.add(partName);

				partDesc = resultSet.getString("Description");
				partDescList.add(partDesc);

				partPrice = Double.parseDouble(resultSet.getString("price"));
				partPriceList.add(partPrice);

				supplierNameP = resultSet.getString("userName");
				supplierNameListP.add(supplierNameP);

			}

			// if (carID == 0) {

			// throw new Exception();
			// }

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in getcarmake");
		}

		ArrayList<Button> buttonList = new ArrayList<>();

		for (int i = 0; i < orderIDList.size(); i++) {
			Button buyButton = new Button(
					"Sold by: " + supplierNameListP.get(i) + '\n' + "Price: $" + partPriceList.get(i)
							+ '\n' + "Part Name: " + partNameList.get(i) + '\n'
							+ "Description: " + partDescList.get(i));
			buttonList.add(buyButton);
			buyButton.setStyle("-fx-background-color: #D3D3D3;");
			buyButton.setPrefSize(200, 200);

			// partPurchasesHBox.getChildren().add(buttonList.get(i));

		}

		System.out.println(orderIDList.size());

	}

	public void SellPage(ActionEvent event) throws IOException {
		Window owner = Sell_Button.getScene().getWindow();
		if (userPreferences.getInt("userID", 0) == 0) {
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
		if (userPreferences.getInt("userID", 0) != 0) {
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
		if (userPreferences.getInt("userID", 0) != 0) {
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