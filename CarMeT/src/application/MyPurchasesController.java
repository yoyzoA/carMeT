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

public class MyPurchasesController /* implements Initializable */ {

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
        Window owner = Sell_Button.getScene().getWindow();
        if (userPreferences.getInt("userID", 0) == 0) {
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

    public void showPurchases(ActionEvent event) throws IOException {
        carPurchasesHBox.getChildren().clear();
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";
        int customerID = userPreferences.getInt("userID", 0);

        // attributes of carOrder

        int orderIDC = 0;
        ArrayList<Integer> orderIDCList = new ArrayList<>();
        String supplierName = "";
        ArrayList<String> supplierNameList = new ArrayList<>();
        String carMake = "";
        ArrayList<String> carMakeList = new ArrayList<>();
        int price = 0;
        ArrayList<Integer> priceList = new ArrayList<>();
        String color = "";
        ArrayList<String> colorList = new ArrayList<>();
        String carDesc = "";
        ArrayList<String> carDescList = new ArrayList<>();

        // attributes of part
        int orderID = 0;
        ArrayList<Integer> orderIDList = new ArrayList<>();
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
                orderIDList.add(orderID);

                partName = resultSet.getString("name");
                partNameList.add(partName);

                partDesc = resultSet.getString("Description");
                partDescList.add(partDesc);

                partPrice = Double.parseDouble(resultSet.getString("price"));
                partPriceList.add(partPrice);

                supplierNameP = resultSet.getString("userName");
                supplierNameListP.add(supplierNameP);

            }

            String sql2 = "select o.orderID, u.userName, c.price, c.color,c.carDescription,cm.makeName from carMake cm, user u , orders o , car c,carorder co where cm.carmakeID=c.carmakeID and co.carID=c.carID and u.userID = co.supplierID and co.orderID=o.orderID and co.customerID=\""
                    + customerID + "\"";
            preparedStatement = connection.prepareStatement(sql2);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                orderIDC = Integer.parseInt(resultSet.getString("orderID"));
                orderIDCList.add(orderID);

                carMake = resultSet.getString("makeName");
                carMakeList.add(carMake);

                carDesc = resultSet.getString("carDescription");
                carDescList.add(carDesc);

                color = resultSet.getString("color");
                colorList.add(color);

                price = Integer.parseInt(resultSet.getString("price"));
                priceList.add(price);

                supplierName = resultSet.getString("userName");
                supplierNameList.add(supplierName);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in getcarmake");
        }

        ArrayList<Button> buttonPList = new ArrayList<>();

        ArrayList<Button> buttonCList = new ArrayList<>();

        for (int i = 0; i < orderIDList.size(); i++) {
            Button buyPButton = new Button(
                    "Sold by: " + supplierNameListP.get(i) + '\n' + "Price: $" + partPriceList.get(i)
                            + '\n' + "Part Name: " + partNameList.get(i) + '\n'
                            + "Description: " + partDescList.get(i));
            buttonPList.add(buyPButton);
            buyPButton.setStyle("-fx-background-color: #D3D3D3;");
            buyPButton.setPrefSize(200, 200);

            partPurchasesHBox.getChildren().add(buttonPList.get(i));

        }

        for (int i = 0; i < orderIDCList.size(); i++) {
            Button buyCButton = new Button(
                    "Sold by: " + supplierNameList.get(i) + '\n' + "Price: $" + priceList.get(i)
                            + '\n' + "Make: " + carMakeList.get(i) + '\n'
                            + "Description: " + carDescList.get(i) + '\n'
                            + "Color: " + colorList.get(i));

            buttonCList.add(buyCButton);
            buyCButton.setStyle("-fx-background-color: #D3D3D3;");
            buyCButton.setPrefSize(200, 200);

            carPurchasesHBox.getChildren().add(buttonCList.get(i));
            stage.setScene(scene);
            stage.show();

        }

        System.out.println(orderIDList.size());
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