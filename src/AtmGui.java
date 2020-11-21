import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AtmGui extends JFrame{
    private JPanel rootPanel;
    private JTabbedPane mainTabbedPane;
    private JPanel UserPanel;
    private JPanel AdminPanel;
    private JTextField PINTextField;
    private JButton enterButton;
    private JLabel accountNumberLabel;
    private JLabel PINLabel;
    private JLabel enterYourDetailsToLabel;
    private JTextField adminPasswordTextField;
    private JButton loginButton;
    private JLabel enterAdminDetailsLabel;
    private JLabel adminUsernameLabel;
    private JLabel adminPasswordLabel;
    private JLabel userLabel;
    private JTextField adminUsernameTextField;
    private JButton closeButton;
    private JTextField accountNumberTextField;
    private JPanel userRootPanel;

    public AtmGui(Connection connection) {
        setSize(600,300);
        setTitle("Banking App");
        add(rootPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Login as user*/
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (accountNumberTextField.getText().trim().isEmpty()||PINTextField.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Fields cannot be empty");
                    }else {
                        if (Customer.validateUserCredentials(connection,Integer.parseInt(accountNumberTextField.getText()),Integer.parseInt(PINTextField.getText()))){
                            new UserDashBoard(connection,Integer.parseInt(accountNumberTextField.getText())).setVisible(true);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Wrong details");
                        }
                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        });

        /*
        * Close the app
        * */
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    connection.close();
                    dispose();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        /*
        * Logging in the admin
        * */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (adminPasswordTextField.getText().trim().isEmpty() || adminUsernameTextField.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Fields cannot be empty");
                    }else {
                        if (Admin.validateAdminCredentials(connection,adminUsernameTextField.getText().trim(),adminPasswordTextField.getText().trim())){
                            new AdminDashboard(connection).setVisible(true);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Wrong details");
                        }
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }
        });
    }
}