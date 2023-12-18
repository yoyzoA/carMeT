package application;

import java.sql.*;

import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.*;

public class LeaveReview {
    int reviewID;
    int supplierID;
    Double rating;
    String comment;
    String Supplier_Username;

    public LeaveReview (String supplier, double rating, String comments) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO REVIEW (rating,comment,reviewDate,supplierID) VALUES ("+rating+",\""+comment+"\",\""+java.time.LocalDate.now()+"\","+supplierID+");";
            statement.execute(sql);
            System.out.println("------------------"+supplier);
            getSupplierID(Supplier_Username);
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void getSupplierID(String supplier_name) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT userID FROM USER WHERE username IN (\""+Supplier_Username+"\");";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.supplierID= Integer.parseInt(resultSet.getString("supplierID"));
                System.out.println("------------------"+supplierID);
                
            }

            
            connection.close();
           
        } catch (SQLException e) {
            printSQLException(e);
        }
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
