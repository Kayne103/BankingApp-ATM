import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    public static double getAccountBalance(Connection connection, int accountID) throws SQLException {
            String query = "select * from Account where Account_Number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, accountID);
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.beforeFirst();
            resultSet.next();
            Double balance = resultSet.getDouble(3);
            return balance;
    }

    public static void AddAccount(Connection connection,int customer_ID,String account_Type){
        try {
            String query = "insert into Account(Customer_ID,Account_Type) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customer_ID);
            statement.setString(2,account_Type);
            statement.execute();
            JOptionPane.showMessageDialog(null,"Account created for customer\nID: "+customer_ID);
            statement.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static ResultSet getAccounts(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from Account");
        return statement.executeQuery();
    }

    /* Returns true if account is locked */
    public static boolean isAccountLocked(Connection connection,int accountNumber)throws SQLException{
        String query = "select Account_Status from Account where Account_Number = ? limit 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,accountNumber);
        if (statement.executeQuery().getString(0).equalsIgnoreCase("locked")){
            return true;
        }else {
            return false;
        }
    }

    public static void withdrawCash(Connection connection,int accountNumber,double drawCash){
        try{
            String query = "update Account set Account_Balance = Account_Balance-? where Account_Number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1,drawCash);
            statement.setInt(2,accountNumber);
            statement.execute();
            JOptionPane.showMessageDialog(null,drawCash+" withdrawn successfully");
            statement.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void depositCash(Connection connection,int accountNumber,double depositCash){
        try{
            String query = "update Account set Account_Balance = Account_Balance+? where Account_Number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1,depositCash);
            statement.setInt(2,accountNumber);
            statement.execute();
            JOptionPane.showMessageDialog(null,depositCash+" successfully deposited");
            statement.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void changePIN(Connection connection,int accountNumber,int newPIN){
        try{
            String query = "update Account set Account_PIN = ? where Account_Number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,newPIN);
            statement.setInt(2,accountNumber);
            statement.execute();
            JOptionPane.showMessageDialog(null,"Your PIN is now changed");
            statement.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static ResultSet getMyAccounts(Connection connection,int UserID)throws SQLException{
        String query = "select Account_Number,Account_Type,Account_Status from Account where Customer_ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,UserID);
        return statement.executeQuery();
    }

}
