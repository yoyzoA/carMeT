package application;

import java.sql.*;

import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class LeaveReview {
    int reviewID;
    int supplierID;
    Double rating;
    String comments;
    String Supplier_Username;
    Preferences userPreferences= Preferences.userRoot();
    int userID=userPreferences.getInt("userID", 0);

    String url = "jdbc:mysql://localhost:3306/carMeT";
    String username0 = "root";
    String password = "151204";

    public LeaveReview (String supplier,double rating, String comments) throws SQLException{
        
        this.rating=rating;
        this.comments=comments;
        this.Supplier_Username=supplier;
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO REVIEW (rating,comment,reviewDate) VALUES ("+rating+",\""+comments+"\",\""+java.time.LocalDate.now()+"\");";
            statement.execute(sql);
            
            connection.close();
            getSupplierID(supplier);
            getMessageID(comments, rating);
            leaves(this.userID,this.reviewID,this.supplierID);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void getSupplierID(String supplier_name) throws SQLException{
        

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT userID FROM USER WHERE username IN (\""+Supplier_Username+"\");";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.supplierID= Integer.parseInt(resultSet.getString("userID"));
                System.out.println("------------------"+supplierID);
                
            }


            
            connection.close();
           
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void getMessageID(String comments, Double rating){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT reviewID FROM REVIEW WHERE comment=\""+comments+"\" AND rating="+rating+";";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.reviewID= Integer.parseInt(resultSet.getString("reviewID"));
                System.out.println("------------------"+supplierID);
                
            }

            
            connection.close();
           
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void leaves(int userID, int reviewID, int SupplierID){
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO leaves (customerID,reviewID,supplierID) VALUES ("+userID+","+reviewID+","+supplierID+");";
            statement.execute(sql);
            
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static List<String> getSuppliersForUser(int userID) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        List<String> suppliers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username0, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT s.username FROM USER s " +
                     "JOIN CARORDER o ON s.userID = o.supplierID " +
                     "WHERE o.customerID= "+userID+" UNION SELECT DISTINCT s.username FROM USER s " +
                     "JOIN PARTORDER p ON s.userID = p.supplierID " +
                     "WHERE p.customerID= "+userID+";")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String supplier=resultSet.getString("username");
                suppliers.add(supplier);
            }

            

        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return suppliers;

    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
