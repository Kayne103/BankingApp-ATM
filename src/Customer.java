import java.sql.*;

public class Customer {

    public static ResultSet getCustomers(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("select * from Customer");
    }

    public static boolean validateUserCredentials(Connection connection,int accountNumber,int accountPIN) throws SQLException {
        String query = "Select * from Account where Account_Number=? and Account_PIN=? limit 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,accountNumber);
        statement.setInt(2,accountPIN);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

    }
}
