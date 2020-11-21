import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDashBoard extends JFrame{
    private JPanel userDashBoardPanel;
    private JLabel balanceLabel;
    private JTextField amountTextField;
    private JButton confirmAndWithdrawButton;
    private JLabel withdrawMoneyLabel;
    private JLabel amountLabel;
    private JLabel depositMoneyLabel;
    private JTextField depositAmountTextField1;
    private JButton depositButton;
    private JLabel accountBalanceLabel;
    private JLabel amountLabel1;
    private JButton printStatementButton;
    private JPanel DepositPanel;
    private JPanel withDrawPanel;
    private JButton quitButton;
    private JButton logoutButton;

    public UserDashBoard(Connection connection,int UserID) throws SQLException {

        setSize(600,300);
        setTitle("Banking App: User");
        add(userDashBoardPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //accountBalanceLabel.setText(Account.getAccountBalance(connection,UserID).getString("Account_Balance"));

        confirmAndWithdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (amountTextField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please provide amount");
                }else{

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
                new AtmGui(connection).setVisible(true);
                dispose();
            }
        });
    }
}