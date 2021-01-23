import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboard extends JFrame{
    private JPanel AdminDashboardPanel;
    private JTabbedPane adminMainTabbedPane;
    private JPanel adminCustomerPanel;
    private JPanel adminAccountsPanel;
    private JTable listOfCustomers;
    private JButton newCustomerButton;
    private JButton createAccountButton;
    private JTextField customerIDTextField;
    private JLabel customerIDLabel;
    private JLabel accountTypeLabel;
    private JComboBox accountTypeComboBox;
    private JButton logoutButton;
    private JButton quitButton;
    private JTable listOfAccounts;
    private JTextField lockAccountNumbertextField;
    private JButton lockButton;
    private JButton unlockButton;
    private JLabel accountNumberLabel;

    String[] typesOfAccounts = {"Cheque","Savings","InterestBearing"};

    public AdminDashboard(Connection connection) throws SQLException {
        setSize(800,500);
        setTitle("Banking App: Admin");
        add(AdminDashboardPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accountTypeComboBox.setModel(new DefaultComboBoxModel(typesOfAccounts)); // Populate combobox with types of accounts
        listOfCustomers.setModel(DbUtils.resultSetToTableModel(Customer.getCustomers(connection))); // Populate table with customers
        listOfAccounts.setModel(DbUtils.resultSetToTableModel(Account.getAccounts(connection)));
        listOfAccounts.setRowSelectionAllowed(true);
        listOfCustomers.setRowSelectionAllowed(true);
        listOfCustomers.selectAll();
        listOfAccounts.selectAll();


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Account.AddAccount(connection,Integer.parseInt(customerIDTextField.getText()), String.valueOf(accountTypeComboBox.getSelectedItem()));

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new AtmGui(connection).setVisible(true);
                    dispose();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

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
                    new AddCustomer(connection,listOfCustomers).setVisible(true);
            }
        });
        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (lockAccountNumbertextField.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Enter account number");
                    }else{
                        Account.unlockAccount(connection,Integer.parseInt(lockAccountNumbertextField.getText()));
                        listOfAccounts.setModel(DbUtils.resultSetToTableModel(Account.getAccounts(connection)));
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        });
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (lockAccountNumbertextField.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Enter account number");
                    }else{
                        Account.lockAccount(connection,Integer.parseInt(lockAccountNumbertextField.getText()));
                        listOfAccounts.setModel(DbUtils.resultSetToTableModel(Account.getAccounts(connection)));
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        });
        listOfCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                customerIDTextField.setText(listOfCustomers.getValueAt(listOfCustomers.getSelectedRow(),0)+"");
            }
        });

        listOfAccounts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                lockAccountNumbertextField.setText(listOfAccounts.getValueAt(listOfAccounts.getSelectedRow(),0)+"");
            }
        });
    }
}
