package application;

import java.sql.*;

public class JDBClass {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            // adding a supplier tupple
            // String sql = "INSERT INTO SUPPLIER VALUES ('S12345', 12345, '123 Main
            // Street', 100, 4)";
            // statement.execute(sql);

            ResultSet resultSet = statement.executeQuery("select * from carmake");

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1));
            }

            connection.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

}
