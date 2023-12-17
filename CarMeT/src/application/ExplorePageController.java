package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExplorePageController {
    private Stage stage;
    private Scene scene;
    public Parent root;
    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        // Other initialization code can be added here
    }

    @FXML
    ApplicationController appc = new ApplicationController();

    public void Home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("HomePage.css").toExternalForm();
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

    public void MyPurchases(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("MyPurchases.css").toExternalForm();
        scene.getStylesheets().add(css);
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

    public void LogInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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

    public void BuyPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BuyPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ExploreBMW(ActionEvent event) throws IOException {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";

        int carID = 0;
        ArrayList<Integer> carIDList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT carID FROM car WHERE carMakeID IN ( SELECT carMakeID FROM carmake where makeName = 'Toyota'); ";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                carID = Integer.parseInt(resultSet.getString("carID"));
                carIDList.add(carID);

            }

            if (carID == 0) {

                throw new Exception();
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in getcarmake");
        }

        // System.out.println(Arrays.toString(carIDList.toArray()));

        ArrayList<Button> buttonList = new ArrayList<>();

        for (int i = 0; i < carIDList.size(); i++) {
            Button bmwButton = new Button("carID:" + carIDList.get(i));
            buttonList.add(bmwButton);
            // Add the button to the center of the GridPane
            gridPane.setRowIndex(buttonList.get(i), 0); // Set the row index
            gridPane.setColumnIndex(buttonList.get(i), i); // Set the column index
            gridPane.getChildren().add(buttonList.get(i));

        }

        System.out.println("bmwtest");
        // bmw.getCarMakeID("Toyota");
    }
}
