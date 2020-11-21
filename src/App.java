import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/BankingApp","phpmyadmin","KillSwitch[103]");

            new AtmGui(connection).setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
