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

import javafx.beans.binding.IntegerBinding;
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

    public void Explore(ActionEvent event) throws IOException {
        gridPane.getChildren().clear();
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";

        int carID = 0;
        ArrayList<Integer> carIDList = new ArrayList<>();
        String makeName = "";
        ArrayList<String> makeNameList = new ArrayList<>();
        String model = "";
        ArrayList<String> modelList = new ArrayList<>();
        String color = "";
        ArrayList<String> colorList = new ArrayList<>();
        int odometer = 0;
        ArrayList<Integer> odometerList = new ArrayList<>();
        int price = 0;
        ArrayList<Integer> priceList = new ArrayList<>();
        String vinNb = "";
        ArrayList<String> vinNbList = new ArrayList<>();
        String carDesc = "";
        ArrayList<String> carDescList = new ArrayList<>();
        String userName = "";
        ArrayList<String> userNameList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object source = event.getSource();
        String buttonText = "";

        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            buttonText = clickedButton.getText();
        }

        try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT * FROM car NATURAL JOIN carmake NATURAL JOIN user WHERE carMakeID IN ( SELECT carMakeID FROM carmake where makeName = \""
                    + buttonText + "\"); ";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                carID = Integer.parseInt(resultSet.getString("carID"));
                carIDList.add(carID);

                makeName = resultSet.getString("makeName");
                makeNameList.add(makeName);

                price = Integer.parseInt(resultSet.getString("price"));
                priceList.add(price);

                model = resultSet.getString("model");
                modelList.add(model);

                vinNb = resultSet.getString("vin");
                vinNbList.add(vinNb);

                odometer = Integer.parseInt(resultSet.getString("odometer"));
                odometerList.add(odometer);

                carDesc = resultSet.getString("carDescription");
                carDescList.add(carDesc);

                color = resultSet.getString("color");
                colorList.add(color);

                userName = resultSet.getString("username");
                userNameList.add(userName);

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
            Button bmwButton = new Button("Owned By: " + userNameList.get(i) + '\n' + "Price: $" + priceList.get(i)
                    + '\n' + "Make: " + makeNameList.get(i) + '\n'
                    + "Model: " + modelList.get(i) + '\n'
                    + "VIN: " + vinNbList.get(i) + '\n'
                    + "Odometer: " + odometerList.get(i) + '\n'
                    + "Description: " + carDescList.get(i) + '\n'
                    + "Color: " + colorList.get(i));
            buttonList.add(bmwButton);
            bmwButton.setStyle("-fx-background-color: #D3D3D3;");
            bmwButton.setPrefSize(200, 200);
            bmwButton.setOnMouseEntered(e -> bmwButton.setStyle("-fx-background-color: #FFFF00;"));
            bmwButton.setOnMouseExited(e -> bmwButton.setStyle("-fx-background-color: #D3D3D3;"));
            // Add the button to the center of the GridPane
            gridPane.setRowIndex(buttonList.get(i), 0); // Set the row index
            gridPane.setColumnIndex(buttonList.get(i), i); // Set the column index
            gridPane.getChildren().add(buttonList.get(i));

        }

        System.out.println("bmwtest");
        // bmw.getCarMakeID("Toyota");
    }
}
