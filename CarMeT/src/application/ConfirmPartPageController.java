package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class ConfirmPartPageController {
    Preferences userPreferences = Preferences.userRoot();
    @FXML
    private Label buyButtonLabelText;

    @FXML
    private Button confirmButton;

    public void setBuyButtonLabelText(String text) {
        buyButtonLabelText.setText(text);
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) {

        Window owner = confirmButton.getScene().getWindow();
        if (userPreferences.getInt("userID", 0) == 0) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please Login to your account");
            return;
        }

        LocalDate today = LocalDate.now();
        String status = "Ordered";
        int price = extractPrice(buyButtonLabelText.getText());
        int userID = userPreferences.getInt("userID", 0);
        String supplierName = extractOwner(buyButtonLabelText.getText());

        int supplierID = 0;
        int carID = 0;
        int orderID = 0;

        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "INSERT INTO ORDERS (date, status, totalAmount) VALUES (\"" + today + "\", \"" + status
                    + "\",\"" + price + "\")";
            preparedStatement = connection.prepareStatement(sql);

            result = preparedStatement.executeUpdate();
            // String sql2 = "SELECT userID, carID, orderID FROM USER NATURAL JOIN ORDERS
            // NATURAL JOIN CAR WHERE userName = \""
            // + supplierName
            // + "\" AND vin = \"" + vin + "\" ";
            // preparedStatement = connection.prepareStatement(sql2);
            // resultSet = preparedStatement.executeQuery();
            // while (resultSet.next()) {
            // supplierID = Integer.parseInt(resultSet.getString("userID"));
            // carID = Integer.parseInt(resultSet.getString("carID"));
            // orderID = Integer.parseInt(resultSet.getString("orderID"));
            // }

            // String sql3 = "INSERT INTO CARORDER (customerID, supplierID, carID, orderID)
            // VALUES (\"" + userID + "\", \""
            // + supplierID
            // + "\",\"" + carID + "\",\"" + orderID + "\")";
            // preparedStatement = connection.prepareStatement(sql3);

            // result = preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println(orderID);

    }

    public void initialize() {
        // Set initial size
        setButtonSize(75.0, 26.0);

        // Handle hover events
        confirmButton.setOnMouseEntered(event -> {
            setButtonSize(100.0, 40.0);
            confirmButton.setLayoutY(407 - 14);
            confirmButton.setLayoutX(170 - 15);
            confirmButton.setStyle("-fx-background-color: #FF0000");
        });

        confirmButton.setOnMouseExited(event -> {
            setButtonSize(75.0, 26.0);
            confirmButton.setLayoutY(407);
            confirmButton.setLayoutX(170);
            confirmButton.setStyle("-fx-background-color: #ffc107");
        });

        // Handle button action
        confirmButton.setOnAction(this::confirmButtonAction);
    }

    private void setButtonSize(double width, double height) {
        confirmButton.setMinWidth(width);
        confirmButton.setMinHeight(height);
        confirmButton.setMaxWidth(width);
        confirmButton.setMaxHeight(height);
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static int extractPrice(String input) {
        Pattern pattern = Pattern.compile("Price: \\$(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String priceString = matcher.group(1);
            return Integer.parseInt(priceString);
        } else {
            return -1;
        }
    }

    public static String extractOwner(String input) {
        String regex = "Owned By: (.+)";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Unknown";
        }
    }

}
