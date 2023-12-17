package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ExplorePage {

    String carMake;
    // String color;
    // String odometer;
    // String price;
    // String vin;
    // String description;
    int carMakeID;
    ArrayList<Integer> resultList = new ArrayList<>();

    public ExplorePage(String carMake) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            // System.out.println("------------------"+userID);
            getCarMakeID(carMake);
            String sql = "SELECT carID FROM car WHERE carMakeID IN (\"" + carMake + "\");";
            statement.execute(sql);

            connection.close();
        } catch (Exception e) {

            System.out.println("error in constructor");
            e.printStackTrace();
        }
    }

    public void getCarMakeID(String carMake) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT carMakeID FROM carMake WHERE makeName IN (\"" + carMake + "\"); ";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                this.carMakeID = Integer.parseInt(resultSet.getString("carMakeID"));
                resultList.add(carMakeID);

            }

            if (this.carMakeID == 0) {

                throw new Exception();
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in getcarmake");
        }

        System.out.println(Arrays.toString(resultList.toArray()));

    }
}
