package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class SellPart {

    int partID;

    public SellPart() {
    }

    public SellPart(int userID, String name, String price, String description) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        try {
            Connection connection = DriverManager.getConnection(url, username0, password);
            Statement statement = connection.createStatement();
            System.out.println("------------------" + userID);

            String sql = "INSERT INTO PART (supplierID,name,description,price) VALUES (" + userID + ",\'" + name
                    + "\',\'" + description + "\'," + price + ");";
            statement.execute(sql);

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void workson(int partID, int[] carmakeIDs) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        try (Connection connection = DriverManager.getConnection(url, username0, password);
                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO WORKSON (partID,carMakeID) VALUES (?, ?)")) {

            // Iterate over the carmakeIDs array and insert each value into WORKSON table
            for (int carmakeID : carmakeIDs) {
                preparedStatement.setInt(1, partID);

                preparedStatement.setInt(2, carmakeID);
                preparedStatement.executeUpdate();
            }

            System.out.println("Data inserted into WORKSON table successfully.");

        } catch (SQLException e) {
            printSQLException(e);
        }
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

    public List<Integer> getCarMakeID(String carMake, String[] carModels) throws SQLException {
        List<Integer> carMakeIDs = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        try (Connection connection = DriverManager.getConnection(url, username0, password);
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT carMakeID FROM CARMAKE WHERE makeName = ? AND model = ?")) {

            for (String carModel : carModels) {
                preparedStatement.setString(1, carMake);
                preparedStatement.setString(2, carModel);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        carMakeIDs.add(resultSet.getInt("carMakeID"));
                    }
                }
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return carMakeIDs;

    }

    public static int getPartID(String partName, int userID) throws SQLException {
        int partID = -1; // Default value if not found
        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";
        try (Connection connection = DriverManager.getConnection(url, username0, password);
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT partID FROM PART WHERE name = ? AND supplierID = ?")) {

            preparedStatement.setString(1, partName);
            preparedStatement.setInt(2, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    partID = resultSet.getInt("partID");

                }
            }

        } catch (SQLException e) {
            printSQLException(e);
        }

        return partID;
    }

    public List<String> getCarModels(String carMake) {
        List<String> carModels = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/carMeT";
        String username0 = "root";
        String password = "151204";

        try (Connection connection = DriverManager.getConnection(url, username0, password);
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT model FROM CARMAKE WHERE makeName = ?")) {

            preparedStatement.setString(1, carMake);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    carModels.add(resultSet.getString("model"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carModels;
    }

}
