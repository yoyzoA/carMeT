package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.prefs.Preferences;
import java.sql.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

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
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ReviewController implements Initializable  {
	@FXML
	TextField Supplier;
	@FXML
	Slider Rating;
	@FXML
	TextArea Comments;
    @FXML
    Button submitButton;

    private Stage stage;
	private Scene scene;
	public Parent root;
	SellPart partsell=new SellPart();
    Preferences userPreferences = Preferences.userRoot();
    int userID = userPreferences.getInt("userID", 0);
	List<String> suppliers = getSuppliersForUser(userID);
	ApplicationController appc = new ApplicationController();   
    

    public void submitReview(ActionEvent event) throws IOException {
        Window owner = submitButton.getScene().getWindow();
        if(suppliers.contains(Supplier.getText())){
		try {
			String supplier_username= Supplier.getText();
			String reviewText = Comments.getText();
            double rating = Rating.getValue();
			Thread.sleep(1000);
			LeaveReview review = new LeaveReview(supplier_username, rating, reviewText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("HomePage.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();}
        else{
            showAlert(Alert.AlertType.ERROR,owner, "Form Error!",
                            "Please enter a valid supplier username");
                    return;
        }

        
	}

    private List<String> getSuppliersForUser(int userId) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
         List<String> suppliers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username0, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT s.username FROM USER s " +
                     "JOIN CARORDER o ON s.user_id = o.supplier_id " +
                     "WHERE o.customer_id = userID")) {

            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String supplier=resultSet.getString("name");
                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
        return suppliers;

    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}
