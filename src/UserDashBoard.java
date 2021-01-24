import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDashBoard extends JFrame{
    private JPanel userDashBoardPanel;
    private JTextField amountTextField;
    private JButton confirmAndWithdrawButton;
    private JLabel amountLabel;
    private JTextField depositAmountTextField1;
    private JButton depositButton;
    private JLabel amountLabel1;
    private JPanel DepositPanel;
    private JPanel withDrawPanel;
    private JButton quitButton;
    private JButton logoutButton;
    private JTextField oldPINTextField;
    private JTextField newPINTextField;
    private JButton changeButton;
    private JPanel PINpanel;
    private JLabel newPINLabel;
    private JLabel oldPINLabel;
    private JButton checkBalanceButton;
    private JTabbedPane userDashboardJTabbedPane;

    public UserDashBoard(Connection connection,int UserID) throws SQLException {

        setSize(800,500);
        setTitle("Banking App: User");
        add(userDashBoardPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //accountBalanceLabel.setText(String.valueOf(Account.getAccountBalance(connection,UserID)));

        confirmAndWithdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (amountTextField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please provide amount");
                }else {
                    try {
                        if (Account.isAccountWithdrawable(Account.getAccountType(connection,UserID))){
                            if (Double.parseDouble(amountTextField.getText())>Account.getAccountBalance(connection,UserID)){
                                JOptionPane.showMessageDialog(null,"Insufficient Balance\nCannot withdraw amount");
                            }else {
                                Account.withdrawCash(connection,UserID,Double.parseDouble(amountTextField.getText()));
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Cannot withdraw from a non-cheque account");
                        }
                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }
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

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (depositAmountTextField1.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please provide amount");
                }else if (Double.parseDouble(depositAmountTextField1.getText())<0){
                    JOptionPane.showMessageDialog(null,"Please provide valid amount");
                }else {
                    Account.depositCash(connection,UserID,Double.parseDouble(depositAmountTextField1.getText()));
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (oldPINTextField.getText().trim().isEmpty() || newPINTextField.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Field cannot be empty");
                    }else if (Customer.validateUserCredentials(connection,UserID,Integer.parseInt(oldPINTextField.getText()))){
                        Account.changePIN(connection,UserID,Integer.parseInt(newPINTextField.getText()));
                    }else{
                        JOptionPane.showMessageDialog(null,"Provide correct password");
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        });
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Your balance is: P"+Account.getAccountBalance(connection,UserID));
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}