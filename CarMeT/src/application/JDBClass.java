package application;

import java.sql.*;

public class JDBClass {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/productdelivery";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from delivery");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDate(3) + " "
                        + resultSet.getInt(4));
            }

            connection.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

}
