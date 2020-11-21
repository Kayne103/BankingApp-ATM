import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    public static ResultSet getAccountBalance(Connection connection, int accountID) throws SQLException {
            String query = "select Account_Balance from Account where Account_Number=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, accountID);
            return stmt.executeQuery(query);
    }

    public static void AddAccount(Connection connection,int customer_ID,String account_Type){
        try {
            String query = "insert into Account(Customer_ID,Account_Type) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customer_ID);
            statement.setString(2,account_Type);
            statement.execute();
            JOptionPane.showMessageDialog(null,"Account created for customer\nID: "+customer_ID);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
