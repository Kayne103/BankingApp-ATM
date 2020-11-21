import javax.swing.*;
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

    public static void AddCustomer(Connection connection,int customerID,String firstname,String lastname,String address){
        try {
            String query = "insert into Customer(Customer_ID,Customer_Firstname,Customer_Lastname,Customer_Address) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customerID);
            statement.setString(2,firstname);
            statement.setString(3,lastname);
            statement.setString(4,address);
            statement.execute();
            JOptionPane.showMessageDialog(null,firstname+" "+lastname+" is now registered.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}