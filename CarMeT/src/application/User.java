package application;

import java.sql.*;
import java.time.*;

public class User {
    int userID = 0;
    String username;
    String userEmail;
    String userPassword;
    String registrationDate;
    int phoneNumber;

    public User() {

    }

    public User(String username, String userEmail, String userPassword, String phoneNumber) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO USER (username,userEmail,userPassword,registrationDate,phoneNumber) VALUES (\""
                    + username + "\",\"" + userEmail + "\",\"" + userPassword + "\",\'" + java.time.LocalDate.now()
                    + "\'," + phoneNumber + ")";
            statement.execute(sql);

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean userSignIn(String uPassword, String email) {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "root";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username0, password);
            String SELECT_QUERY = "SELECT * FROM USER WHERE userEmail = ? and userPassword = ?";
            preparedStatement = connection.prepareStatement(SELECT_QUERY);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, uPassword);

            // Process the result set
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.userID = Integer.parseInt(resultSet.getString("userID"));
                this.username = resultSet.getString("username");
                return true;
            }

            connection.close();

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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

    public int getuserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}