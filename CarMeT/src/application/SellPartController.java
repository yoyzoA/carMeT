package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SellPartController implements Initializable {

    private Stage stage;
    private Scene scene;
    public Parent root;

    String carm;
    // List<String> carList = new ArrayList<>();
    private ObservableList<String> carList;
    Preferences userPreferences = Preferences.userRoot();
    SellPart partsell = new SellPart();
    @FXML
    private ChoiceBox<String> makeCB;
    @FXML
    private ListView<String> listView;
    @FXML
    TextField username_textfield;
    @FXML
    TextField username_textfield11;
    @FXML
    TextArea description_txt;

    public void HomeSellPart(ActionEvent event) throws IOException {
        try {
            // Get the selected items from the ListView
            ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
            
            // Create an ArrayList to store the selected items
            List<String> selectedList = new ArrayList<>(selectedItems);
            String[] arr=selectedList.toArray(new String[0]);
            // Now you have the selected items in the ArrayList
            System.out.println("Selected Items: " + selectedList);
            String carMake = carm;
            String name = username_textfield.getText();
            String price = username_textfield11.getText();
            String description = description_txt.getText();
            Thread.sleep(1000);
            int userID = userPreferences.getInt("userID", 0);
            System.out.println("-----------------------ID"+userID);
            SellPart partsell1 = new SellPart(userID, name, price, description);
            int partID= partsell.getPartID(name, userID);
            partsell.workson(partID, partsell.getCarMakeID(carMake, arr).stream().mapToInt(Integer::intValue).toArray());
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
        makeCB.getItems().addAll("Audi", "Bentley", "BMW", "Fiat", "Kia", "Mercedes", "Tesla");
        carList = FXCollections.observableArrayList();
        listView.setItems(carList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        makeCB.setOnAction(this::getCarMake);
    }

    public void getCarMake(ActionEvent event) {
        String carMakeString = makeCB.getValue();
        
        carList.clear();
        carList.addAll(partsell.getCarModels(carMakeString));
        System.out.println(carList.toString());
        carm = carMakeString;

    }

    @FXML
    private void saveSelection() {
        // Get the selected items from the ListView
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();

        // Create a Set to store the selected items
        Set<String> selectedSet = new HashSet<>(selectedItems);

        // Now you have the selected items in the Set
        System.out.println("Selected Items: " + selectedSet);
    }
}
