
package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author ARUNKUMAR
 */
class withdrawalPage extends JFrame{
    
    JPanel border_W_Panel, border_N_Panel, border_E_Panel, border_S_Panel, content_pane, center_Panel ;
    JLabel side_Label, name, accNum, pinNumLabel, waitSeconds, textDisableLabel, withdrawalAmountLabel, withdrawal_waitSeconds;
    JTextField pinNumTextField, withdrawalAmountTextField ;
    JButton submitButton, withdrawalButton, back ;
    
    Double currentBalance = 00.00;
    String accountHolderPinNumber = "";
    withdrawalPage(){
        super ("Withdrawal Page");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        // West Panel
        border_W_Panel = new JPanel();
        border_W_Panel.setBounds(0, 0, 200, 660);
        border_W_Panel.setBackground(Color.BLUE);
        border_W_Panel.setLayout(null);

        side_Label = new JLabel("Withdrawal");
        side_Label.setFont(new Font("Arial", Font.BOLD, 24));
        side_Label.setBounds(35, 300, 200, 40);
        side_Label.setForeground(Color.WHITE);
        border_W_Panel.add(side_Label);
        add(border_W_Panel);

        // Borders
        border_N_Panel = new JPanel();
        border_N_Panel.setBounds(0, 0, 585, 5);
        border_N_Panel.setBackground(Color.BLUE);
        add(border_N_Panel);

        border_E_Panel = new JPanel();
        border_E_Panel.setBounds(580, 0, 5, 660);
        border_E_Panel.setBackground(Color.BLUE);
        add(border_E_Panel);

        border_S_Panel = new JPanel();
        border_S_Panel.setBounds(0, 656, 585, 5);
        border_S_Panel.setBackground(Color.BLUE);
        add(border_S_Panel);

        back = new JButton("back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setBounds(110, 620, 70, 30);
        border_W_Panel.add(back);
        
        // Click the back button
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                
                dispose();
            }
        });
        
        // Content Panel
        content_pane = new JPanel();
        content_pane.setBounds(200, 0, 380, 660);
        content_pane.setBackground(Color.WHITE);
        content_pane.setLayout(null);
        add(content_pane);

        name = new JLabel("Withdrawal");
        name.setFont(new Font("Arial", Font.BOLD, 24));
        name.setForeground(Color.BLUE);
        name.setBounds(120, 40, 200, 40);
        content_pane.add(name);

        
        try (java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
             Statement stmt = con.createStatement()) {

            String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage3";
            ResultSet rs = stmt.executeQuery(getCountIdQuery);
            int latestId = 0;

            if (rs.next()) {
                latestId = rs.getInt("max_id");
            }

            String getPinNumberQuery = "SELECT PinNumber FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getPinNumberQuery);

            if (rs.next()) {
                accountHolderPinNumber = rs.getString("PinNumber");
            }

            //Card Number
            String getCardNumberQuery = "SELECT CardNumber FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getCardNumberQuery);

            String cardNumber = rs.next() ? rs.getString("CardNumber") : "Error fetching card number";
            accNum = new JLabel("Card Number    : " + cardNumber);
            
            // Balance 
            String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getBalanceQuery);

           

            if (rs.next()) {
               currentBalance = rs.getDouble("Balance");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            accNum = new JLabel("Error fetching data");
        }
        //accNum = new JLabel("Acc Num           : " + "XXXXX");
        accNum.setFont(new Font("Arial", Font.PLAIN, 21));
        accNum.setForeground(Color.BLUE);
        accNum.setBounds(30, 110, 360, 40);
        content_pane.add(accNum);

        pinNumLabel = new JLabel("PIN                   :");
        pinNumLabel.setForeground(Color.BLACK);
        pinNumLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        pinNumLabel.setBounds(30, 160, 170, 30);
        content_pane.add(pinNumLabel);

        pinNumTextField = new JTextField(15);
        pinNumTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        pinNumTextField.setBounds(190, 160, 160, 28);
        content_pane.add(pinNumTextField);

        submitButton = createStyledButton("Submit");
        submitButton.setBounds(110, 230, 160, 38);
        content_pane.add(submitButton);

        waitSeconds = new JLabel("Please Wait ...");
        waitSeconds.setBounds(120, 280, 270, 30);
        waitSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        waitSeconds.setForeground(new Color(253, 170, 62));
        waitSeconds.setVisible(false);
        content_pane.add(waitSeconds);

        // Center Panel (color line)
        center_Panel = new JPanel();
        center_Panel.setBounds(15, 322, 355, 1);
        center_Panel.setBackground(Color.BLUE);
        content_pane.add(center_Panel);
        
        textDisableLabel = new JLabel("Disable Mode");
        textDisableLabel.setBounds(120, 370, 270, 30);
        textDisableLabel.setFont(new Font("Arial", Font.BOLD, 23));
        textDisableLabel.setForeground(new Color(253, 170, 62));
        textDisableLabel.setVisible(true);
        content_pane.add(textDisableLabel);
        
        withdrawalAmountLabel = new JLabel("Enter amount in multiples of 100, 200 OR 500 only");
        withdrawalAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        withdrawalAmountLabel.setForeground(Color.BLUE);
        withdrawalAmountLabel.setBounds(2, 380, 380, 30);
        withdrawalAmountLabel.setVisible(false);
        content_pane.add(withdrawalAmountLabel);

        withdrawalAmountTextField = new JTextField(15);
        withdrawalAmountTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawalAmountTextField.setBounds(110, 450, 160, 28);
        withdrawalAmountTextField.setVisible(false);
        
        content_pane.add(withdrawalAmountTextField);

        withdrawalButton = createStyledButton("Withdraw");
        withdrawalButton.setBounds(110, 520, 160, 38);
        withdrawalButton.setVisible(false);
        content_pane.add(withdrawalButton);
        
        withdrawal_waitSeconds = new JLabel("Please Wait ...");
        withdrawal_waitSeconds.setBounds(120, 590, 270, 30);
        withdrawal_waitSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        withdrawal_waitSeconds.setForeground(new Color(253, 170, 62));
        withdrawal_waitSeconds.setVisible(false);
        content_pane.add(withdrawal_waitSeconds);

        // PIN Validation
        submitButton.addActionListener(e -> {
            String enteredPin = pinNumTextField.getText();
            if (enteredPin.isEmpty()) {
                pinNumTextField.setText("");
                JOptionPane.showMessageDialog(null, "Please enter a PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!enteredPin.equals(accountHolderPinNumber)) {
                pinNumTextField.setText("");
                JOptionPane.showMessageDialog(null, "Incorrect PIN! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            waitSeconds.setVisible(true);
            Timer timer = new Timer(2000, evt -> {
                pinNumTextField.setText("");
                
                waitSeconds.setVisible(false);
            
                JOptionPane.showMessageDialog(null, "PIN Validated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                     
                textDisableLabel.setVisible(false);
                withdrawalAmountLabel.setVisible(true);
                withdrawalAmountTextField.setVisible(true);
                withdrawalButton.setVisible(true);
                
                ((Timer) evt.getSource()).stop();
            });

            timer.setRepeats(false);
            timer.start();
        });

    withdrawalButton.addActionListener(e -> {
    String amountText = withdrawalAmountTextField.getText();
    int amount;

    try {
        amount = Integer.parseInt(amountText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (amount <= 0 || (amount % 100 != 0 && amount % 200 != 0 && amount % 500 != 0)) {
        withdrawalAmountTextField.setText("");
        JOptionPane.showMessageDialog(null, "Amount must be a positive multiple of 100, 200, or 500!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    withdrawal_waitSeconds.setVisible(true);

    Timer timer = new Timer(2000, evt -> {
        withdrawal_waitSeconds.setVisible(false);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
             Statement stmt = con.createStatement()) {

            // Step 1: Get latest account ID
            String getLatestIdQuery = "SELECT MAX(id) AS max_id FROM accountPage3";
            ResultSet rs = stmt.executeQuery(getLatestIdQuery);
            int latestId = 0;
            if (rs.next()) {
                latestId = rs.getInt("max_id");
            }

            // Step 2: Get current balance
            double currentBalance = 0.0;
            String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getBalanceQuery);
            if (rs.next()) {
                currentBalance = rs.getDouble("Balance");
            }

            // Step 3: Check balance and update
            if (currentBalance >= amount) {
                double newBalance = currentBalance - amount;
                String updateBalanceQuery = "UPDATE accountPage3 SET Balance = " + newBalance + " WHERE id = " + latestId;
                stmt.executeUpdate(updateBalanceQuery);

                JOptionPane.showMessageDialog(null, "✅  Rs: " + amount + " successfully withdrawn!", "Withdrawal", JOptionPane.INFORMATION_MESSAGE);

                //  ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++ >>>
                // Step 4: Insert into mini statement
                LocalDateTime nowDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = nowDateTime.format(formatter);

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059")) {
                    // Get latest ID from accountPage1
                    int accountId = 0;
                    String getIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
                    try (PreparedStatement pstmt1 = conn.prepareStatement(getIdQuery);
                         ResultSet rs1 = pstmt1.executeQuery()) {
                        if (rs1.next()) {
                            accountId = rs1.getInt("max_id");
                        }
                    }

                    // Get Name
                    String Name = "";
                    String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = ?";
                    try (PreparedStatement pstmt2 = conn.prepareStatement(getNameQuery)) {
                        pstmt2.setInt(1, accountId);
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            if (rs2.next()) {
                                Name = rs2.getString("Name");
                            } else {
                                Name = "Unknown";
                            }
                        }
                    }

                    // Get updated balance
                    double updatedBalance = 0.0;
                    String getUpdatedBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = ?";
                    try (PreparedStatement pstmt3 = conn.prepareStatement(getUpdatedBalanceQuery)) {
                        pstmt3.setInt(1, accountId);
                        try (ResultSet rs3 = pstmt3.executeQuery()) {
                            if (rs3.next()) {
                                updatedBalance = rs3.getDouble("Balance");
                            }
                        }
                    }

                    // Insert mini statement
                    String miniStatementTable = Name + "_miniStatement";
                    String insertQuery = "INSERT INTO " + miniStatementTable + " (DATE, PARTICULARS, DEBIT, CREDIT, BALANCE) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt4 = conn.prepareStatement(insertQuery)) {
                        pstmt4.setString(1, formattedDateTime);
                        pstmt4.setString(2, Name+" Withdrawn");
                        pstmt4.setDouble(3, amount);
                        pstmt4.setNull(4, java.sql.Types.DOUBLE); // null
                        pstmt4.setDouble(5, updatedBalance);
                        pstmt4.executeUpdate();
                    }

                } catch (SQLException miniEx) {
                    miniEx.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Mini statement insert error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
// <<<< ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++
                withdrawalAmountTextField.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Insufficient Balance!", "Error", JOptionPane.ERROR_MESSAGE);
                withdrawalAmountTextField.setText("");
            }

        } catch (SQLException ex2) {
            ex2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error accessing database!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        ((Timer) evt.getSource()).stop();
    });

    timer.setRepeats(false);
    timer.start();
});



        setVisible(true);
    }

    // Method to create styled buttons
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded rectangle background
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border needed
            }
        };
        
        // Hover effect for button1
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(19, 86,243));
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(81, 142,247));
            }
        }); 
        
        
         // Set button properties
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground( new Color(81, 142,247));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        
        return button;
        
        
    }
    
    public static void main(String[] args){
        new withdrawalPage();
    }
}
