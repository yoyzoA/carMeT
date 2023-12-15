package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class ExplorePageController {
    private Stage stage;
    private Scene scene;
    public Parent root;

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
        ExplorePage bmw = new ExplorePage("Toyota");
        System.out.println("bmwtest");
        // bmw.getCarMakeID("Toyota");
    }
}
