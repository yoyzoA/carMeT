package application;

import java.sql.*;
import java.time.*;

public class JDBClass {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            // adding a supplier tupple
            // String sql1 = "INSERT INTO USER (userID) VALUES (12345)";
            // statement.execute(sql1);
            int userID;
            String username = "hello";
            String userEmail = "hello@yahoo.com";
            String userPassword = "occke";
            String registrationDate;
            int phoneNumber = 961747949;
            String sql = "INSERT INTO USER (username,userEmail,userPassword,registrationDate,phoneNumber) VALUES (\""
                    + username + "\",\"" + userEmail + "\",\"" + userPassword + "\",\'" + java.time.LocalDate.now()
                    + "\'," + phoneNumber + ")";
            statement.execute(sql);

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
