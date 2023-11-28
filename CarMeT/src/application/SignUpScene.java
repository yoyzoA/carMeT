package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class SignUpScene{

    @FXML
    TextField signUpUsername;
    TextField signUpEmail;
    TextField signUpPassword;
    TextField signUpPhoneNumber;
    private Button cancelSignUp;
    private Button signUpDone;


    private Stage stage;
    private Scene scene;
    private Parent root;
    

    public void cancelSignUp(ActionEvent e){
        Stage stage =(Stage) cancelSignUp.getScene().getWindow();
        stage.close();
    }

    public void signUpDone(ActionEvent event) throws IOException{

        String username = signUpUsername.getText();
        String email = signUpEmail.getText();
        String password = signUpPassword.getText();
        String phoneNumber = signUpPhoneNumber.getText();
        System.out.println(username+password+email+phoneNumber);
        
       User user = new User(username,email,password,phoneNumber);

    }

}