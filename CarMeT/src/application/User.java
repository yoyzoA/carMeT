package application;
import java.sql.*;
import java.time.*;  
public class User{
    int userID=0;
    String username;
    String userEmail;
    String userPassword;
    String registrationDate;
    int phoneNumber;

    public User(){

    }
    
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
    public void userSignIn(String username,String userPassword,String userEmail){
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


            try {
            connection = DriverManager.getConnection(url, username0, password);
            String sql = "SELECT userID FROM user WHERE username IN (\""+username+"\") AND userPassword IN(\""+userPassword+"\") AND userEmail IN (\""+userEmail+"\");";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve values from the result set
                this.userID= Integer.parseInt(resultSet.getString("userID"));
            }

            if(this.userID==0){
                throw new Exception();
            }
            connection.close();
           
        } catch (Exception e) {

            System.out.println("wrong username or password");
            

        }
    }
}