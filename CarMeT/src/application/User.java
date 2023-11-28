package application;
import java.sql.*;
import java.time.*;  
public class User{
    int userID;
    String username;
    String userEmail;
    String userPassword;
    String registrationDate;
    int phoneNumber;
    
    public User(String username, String userEmail, String userPassword,String phoneNumber){
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO USER (username,userEmail,userPassword,registrationDate,phoneNumber) VALUES (\""+username+"\",\""+userEmail+"\",\""+userPassword+"\",\'"+java.time.LocalDate.now()+"\',"+phoneNumber+")";
            statement.execute(sql);

            connection.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}