package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SellPartController implements Initializable{
    
    private Stage stage;
	private Scene scene;
	public Parent root;

    String carm;
    List<String> carList = new ArrayList<>();

    Preferences userPreferences=Preferences.userRoot();
    

    @FXML
    private ChoiceBox <String> makeCB;
    @FXML
    private ComboBox <String> makeCombo;
    @FXML
    TextField username_textfield;
    @FXML
    TextField username_textfield11;
    @FXML
    TextArea description_txt;
    
    public void HomeSellPart(ActionEvent event) throws IOException {
		try {
			String carMake = carm;
			String name = username_textfield.getText();
			String price = username_textfield11.getText();
			String description = description_txt.getText();
			Thread.sleep(1000);
            int userID=userPreferences.getInt("userID",0);
			SellPart partsell = new SellPart(userID, name,price,description);
            
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
    public void initialize(URL location, ResourceBundle resources) {
        makeCB.getItems().addAll("Audi","Bentley","BMW","Fiat","Kia","Mercedes","Tesla");
        makeCombo.getItems().addAll(carList);
        makeCB.setOnAction(this::getCarMake);
   }
       public void getCarMake(ActionEvent event){
       String carMakeString=makeCB.getValue();
       	SellPart partsell = new SellPart();

       carList=partsell.getCarModels(carMakeString);
       carm=carMakeString;
       makeCombo.setDisable(false);
       
   }
}   
