import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/BankingApp",DatabaseInfo.UserName,DatabaseInfo.Password);

            new AtmGui(connection).setVisible(true);

        } catch (Exception e) {
            String message = "This App requires database connection to run\n" +
                    "Please make sure the connection url is correct.\n" +
                    "Error:"+e.getMessage();
            JOptionPane.showMessageDialog(null,message);
        }
    }
}
