import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public static boolean validateAdminCredentials(Connection connection,String adminUsername,String adminPassword) throws SQLException {
        String query = "Select * from Admin where Admin_Username=? and Admin_Password=? limit 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,adminUsername);
        statement.setString(2,adminPassword);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

    }
}
