import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {

        try {
            String UserName = "phpmyadmin";
            String Password = "KillSwitch[103]";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/BankingApp",UserName,Password);

            new AtmGui(connection).setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
