package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;



public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//creating label email 
		      Text text1 = new Text("Email");       
		      
		      //creating label password 
		      Text text2 = new Text("Password"); 
		       
		      //Creating Text Filed for email        
		      TextField textField1 = new TextField();       
		      
		      //Creating Text Filed for password        
		      PasswordField textField2 = new PasswordField();  
		       
		      //Creating Buttons 
		      Button button1 = new Button("Submit"); 
		      Button button2 = new Button("Sign Up");  
		      
		      button2.setOnAction(new EventHandler<ActionEvent>() {
		    	    public void handle(ActionEvent event) {
		    	        Parent root;
		    	        try {
		    	            root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
		    	            Stage stage = new Stage();
		    	            stage.setTitle("Sign Up to Car Me-T");
		    	            stage.setScene(new Scene(root, 650, 450));
		    	            stage.show();
		    	            // Hide this current window (if this is what you want)
		    	            ((Node)(event.getSource())).getScene().getWindow().hide();
		    	        }
		    	        catch (IOException e) {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	});
		      
		      //Creating a Grid Pane 
		      GridPane gridPane = new GridPane();    
		      
		      //Setting size for the pane 
		      gridPane.setMinSize(400, 200);
		      
		      //Setting the padding  
		      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		      
		      //Setting the vertical and horizontal gaps between the columns 
		      gridPane.setVgap(5); 
		      gridPane.setHgap(5);       
		      
		      //Setting the Grid alignment 
		      gridPane.setAlignment(Pos.CENTER); 
		       
		      //Arranging all the nodes in the grid 
		      gridPane.add(text1, 0, 0); 
		      gridPane.add(textField1, 1, 0); 
		      gridPane.add(text2, 0, 1);       
		      gridPane.add(textField2, 1, 1); 
		      gridPane.add(button1, 0, 2); 
		      gridPane.add(button2, 1, 2); 
		       
		      
		       
		      // Creating a scene object 
		      Scene scene = new Scene(gridPane); 
		     
		      // Setting title to the Stage   
		      stage.setTitle("Car Me-T"); 
		         
		      // Adding scene to the stage 
		      stage.setScene(scene);
		      
		      //Displaying the contents of the stage 
		      stage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
