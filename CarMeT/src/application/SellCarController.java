package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SellCarController implements Initializable {
    	//Sell car page
	String carm;
	@FXML
	TextField carColor_textfield;
	@FXML
	TextField odometer_textfield;
	@FXML
	TextField price_textfield;
	@FXML
	TextField vin_textfield;
	@FXML
	TextArea description_text;
	@FXML
	private ChoiceBox <String> carMakeCB;

    private Stage stage;
	private Scene scene;
	public Parent root;

    Preferences userPreferences=Preferences.userRoot();
    ApplicationController appc=new ApplicationController();
    
    public void HomeSellCar(ActionEvent event) throws IOException {
		try {
			String carMake = carm;
			String color = carColor_textfield.getText();
			String odometer = odometer_textfield.getText();
			String price = price_textfield.getText();
			String vin=vin_textfield.getText();
			String description = description_text.getText();
			Thread.sleep(1000);
            int userID=userPreferences.getInt("userID",0);
			SellCar carsell = new SellCar(userID, carMake, color, odometer,price,vin,description);
		} catch (Exception e) {
			e.printStackTrace();
		}
        		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("HomePage.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carMakeCB.getItems().addAll("Audi","Bentley","BMW","Fiat","Kia","Mercedes","Tesla");
	 	carMakeCB.setValue("Audi");
	 	carMakeCB.setOnAction(this::getCarMake);
    }
    	public void getCarMake(ActionEvent event){
		String carMakeString=carMakeCB.getValue();
		carm=carMakeString;
	}

}

