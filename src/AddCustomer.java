import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AddCustomer extends JFrame{
    private JPanel AddCustomerPanel;
    private JTextField customerIDTextField;
    private JTextField lastnameTextField;
    private JTextField firstnameTextField;
    private JButton registerButton;
    private JTextField addressTextField;
    private JButton cancelButton;
    private JLabel customerIDLabel;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel addressLabel;

    public AddCustomer(Connection connection) {
        setTitle("Register New Customer");
        setSize(310,340);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(AddCustomerPanel);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean emptyCustomerID = customerIDTextField.getText().trim().isEmpty();
                boolean emptyCustomerName = firstnameTextField.getText().trim().isEmpty();
                boolean emptyLastname = lastnameTextField.getText().trim().isEmpty();
                boolean emptyAddress = addressTextField.getText().trim().isEmpty();

                if (emptyCustomerID||emptyCustomerName||emptyLastname||emptyAddress){
                    JOptionPane.showMessageDialog(null,"Fields cannot be empty");
                }else {
                    Customer.AddCustomer(connection,Integer.parseInt(customerIDTextField.getText()),firstnameTextField.getText(),lastnameTextField.getText(),addressTextField.getText());
                    dispose();
                }
            }
        });
    }
}
