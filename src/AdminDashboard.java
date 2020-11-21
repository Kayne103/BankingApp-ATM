import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboard extends JFrame{
    private JPanel AdminDashboardPanel;
    private JTabbedPane tabbedPane1;
    private JPanel adminCustomerPanel;
    private JPanel adminAccountsPanel;
    private JTable listOfCustomers;
    private JButton newCustomerButton;
    private JLabel createCustomerAccountsLabel;
    private JLabel customersLabel;
    private JButton createAccountButton;
    private JTextField customerIDTextField;
    private JLabel customerIDLabel;
    private JLabel accountTypeLabel;
    private JComboBox accountTypeComboBox;
    private JButton logoutButton;
    private JButton quitButton;
    private JTable listOfAccounts;

    String[] typesOfAccounts = {"Cheque","Savings","InterestBearing"};

    public AdminDashboard(Connection connection) throws SQLException {
        setSize(800,500);
        setTitle("Banking App: Admin");
        add(AdminDashboardPanel);
        setResizable(false);
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accountTypeComboBox.setModel(new DefaultComboBoxModel(typesOfAccounts)); // Populate combobox with types of accounts
        listOfCustomers.setModel(DbUtils.resultSetToTableModel(Customer.getCustomers(connection))); // Populate table with customers
        listOfAccounts.setModel(DbUtils.resultSetToTableModel(Account.getAccounts(connection)));

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Account.AddAccount(connection,Integer.parseInt(customerIDTextField.getText()), String.valueOf(accountTypeComboBox.getSelectedItem()));

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AtmGui(connection).setVisible(true);
                dispose();
            }
        });
        quitButton.addActionListener(new ActionListener() {
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
        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddCustomer(connection).setVisible(true);
                listOfCustomers.repaint();
            }
        });
    }
}
