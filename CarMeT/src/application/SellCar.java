package application;

import java.sql.*;

public class SellCar {
    int userID;
    int carID;
    String carMake;
	String color;
	String odometer;
	String price;
	String vin;
	String description;
    int carMakeID;
    public SellCar(int userID,String carMake, String color, String odometer,String price,String vin,String description)throws SQLException{
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            System.out.println("------------------"+userID);
            getCarMakeID(carMake);
            String sql = "INSERT INTO CAR (userID,carMakeID,color,price,vin,carDescription,odometer) VALUES ("+userID+","+carMakeID+",\'"+color+"\',"+price+",\""+vin+"\",\'"+description+"\',"+odometer+");";
            statement.execute(sql);

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void getCarMakeID(String carMake) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT carMakeID FROM carMake WHERE makename IN (\""+carMake+"\");";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                this.carMakeID= Integer.parseInt(resultSet.getString("carMakeID"));
                System.out.println("------------------"+carMakeID);
                
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
