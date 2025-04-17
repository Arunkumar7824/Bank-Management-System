
package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
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
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.text.AbstractDocument;


class fastcashPage extends JFrame {
    JLabel accNum, PinNumLabel, waitSeconds, above_Label, waitingSeconds;
    JTextField PinNum_TextField;
    JPanel border_W_Panel, border_N_Panel, border_E_Panel, border_S_Panel, content_pane;
    JButton submitButton, denom_100, denom_1000, denom_200, denom_2000, denom_500, denom_5000, denom_10000, back; 
    
    Double currentBalance = 00.00;
    String accountHolderPinNumber = "";
    fastcashPage(){
        super ("FastCash Page");    
        setSize(600, 700);  
        setLocationRelativeTo(null); // Center the window  
        setLayout(null); // Use absolute positioning  

        // West Panel (Left Border)
        border_W_Panel = new JPanel();
        border_W_Panel.setBounds(0, 0, 5, 660);
        border_W_Panel.setBackground(Color.BLUE);
        add(border_W_Panel);

        // North Panel (Top Border)
        border_N_Panel = new JPanel();
        border_N_Panel.setBounds(0, 0, 585, 120);
        border_N_Panel.setBackground(Color.BLUE);
        border_N_Panel.setLayout(null);  // Important for label positioning
        add(border_N_Panel);

        // East Panel (Right Border)
        border_E_Panel = new JPanel();
        border_E_Panel.setBounds(580, 0, 5, 660);
        border_E_Panel.setBackground(Color.BLUE);
        add(border_E_Panel);

        // South Panel (Bottom Border)
        border_S_Panel = new JPanel();
        border_S_Panel.setBounds(0, 656, 585, 5);
        border_S_Panel.setBackground(Color.BLUE);
        add(border_S_Panel);

        back = new JButton("back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(450, 600, 100, 30);
        add(back);
        
        // Click the back button
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                
                dispose();
            }
        });
        
        // FastCash Page Label
        above_Label = new JLabel("FastCash");
        above_Label.setFont(new Font("Arial", Font.BOLD, 24));
        above_Label.setBounds(235, 40, 200, 30); // Adjusted for better positioning
        above_Label.setForeground(Color.WHITE);
        border_N_Panel.add(above_Label);

        // ---------------- Content Panel ----------------//
        content_pane = new JPanel();
        content_pane.setBounds(0, 120, 580, 540);
        content_pane.setBackground(Color.WHITE);
        content_pane.setLayout(null);
        add(content_pane); 
        
        //================
        accNum = new JLabel("Card Number    : Loading...");
        accNum.setFont(new Font("Arial", Font.BOLD, 21));
        accNum.setForeground(Color.BLUE);
        accNum.setBounds(130, 80, 360, 40);
        content_pane.add(accNum);
        
         // Pin Number Label
        PinNumLabel = new JLabel("Enter Your Pin    : ");
        PinNumLabel.setForeground(Color.BLACK);
        PinNumLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        PinNumLabel.setBounds(130, 140, 180, 30);
        PinNumLabel.setVisible(true);
        content_pane.add(PinNumLabel);

        // Pin Number Field
        PinNum_TextField = new JTextField(15);
        PinNum_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        PinNum_TextField.setBounds(320, 140, 160, 28);
        PinNum_TextField.setFocusable(true);
        PinNum_TextField.setBackground(Color.WHITE);
        PinNum_TextField.setVisible(true);
        content_pane.add(PinNum_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) PinNum_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        
        // submit Button
        submitButton = createStyledSubmitButton("Submit");
        submitButton.setMargin(new Insets(6, 32, 6, 32));
        submitButton.setBounds(230,240,160,38);
        submitButton.setVisible(true);
        content_pane.add(submitButton);
        
        
        
        waitSeconds = new JLabel("Please Wait ...");
        waitSeconds.setBounds(240, 320, 270, 30);
        waitSeconds.setFont(new Font("Arial", Font.PLAIN, 22));
        waitSeconds.setForeground( new Color(253,170,62));
        waitSeconds.setVisible(false);
        content_pane.add(waitSeconds);
        


// Now do DB operations and update accNum text
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
    accNum.setText("Card Number    :     " + cardNumber); // ✅ Update label

    // Balance 
    String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
    rs = stmt.executeQuery(getBalanceQuery);

    if (rs.next()) {
       currentBalance = rs.getDouble("Balance");
    }

} catch (SQLException e) {
    e.printStackTrace();
    accNum.setText("Error fetching data");
}

        //=================
        
        waitingSeconds = new JLabel("Please Wait ..."); 
        waitingSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        waitingSeconds.setForeground(new Color(253,170,62));
        waitingSeconds.setBounds(220, 440, 220, 45);
        waitingSeconds.setVisible(false);
        content_pane.add(waitingSeconds);

        denom_100 = createStyledFastCashButton("Rs 100");
        denom_100.setBounds(40,100,160,40);
        denom_100.setVisible(false);
        content_pane.add(denom_100);
        
        denom_1000 = createStyledFastCashButton("Rs 1000");
        denom_1000.setBounds(380,100,160,40);
        denom_1000.setVisible(false);
        content_pane.add(denom_1000);
        
        denom_200 = createStyledFastCashButton("Rs 200");
        denom_200.setBounds(40,180,160,40);
        denom_200.setVisible(false);
        content_pane.add(denom_200);
        
        denom_2000 = createStyledFastCashButton("Rs 2000");
        denom_2000.setBounds(380,180,160,40);
        denom_2000.setVisible(false);
        content_pane.add(denom_2000);
        
        denom_500 = createStyledFastCashButton("Rs 500");
        denom_500.setBounds(40,260,160,40);
        denom_500.setVisible(false);
        content_pane.add(denom_500);
        
        denom_5000 = createStyledFastCashButton("Rs 5000");
        denom_5000.setBounds(380,260,160,40);
        denom_5000.setVisible(false);
        content_pane.add(denom_5000);
        
        denom_10000 = createStyledFastCashButton("Rs 10000");
        denom_10000.setBounds(210,340,160,40);
        denom_10000.setVisible(false);
        content_pane.add(denom_10000);

         // Submit Button Action
        submitButton.addActionListener(e -> {
            String enteredPin = PinNum_TextField.getText();
            if (enteredPin.isEmpty()) {
                PinNum_TextField.setText("");
                JOptionPane.showMessageDialog(null, "Please enter a PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!enteredPin.equals(accountHolderPinNumber)) {
                PinNum_TextField.setText("");
                JOptionPane.showMessageDialog(null, "Incorrect PIN! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            waitSeconds.setVisible(true);
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    PinNum_TextField.setText(accountHolderPinNumber);
                    waitSeconds.setVisible(false);
                    
                    JOptionPane.showMessageDialog(null, "PIN Validated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    accNum.setVisible(false);
                    PinNumLabel.setVisible(false);
                    PinNum_TextField.setVisible(false);
                    submitButton.setVisible(false);
                     
                    denom_100.setVisible(true);
                    denom_1000.setVisible(true);
                    denom_200.setVisible(true);
                    denom_2000.setVisible(true);
                    denom_500.setVisible(true);
                    denom_5000.setVisible(true);
                    denom_10000.setVisible(true);
                }
            }.execute();
        });
        
        // Attach action listeners to each denomination button
        denom_100.addActionListener(e -> handleWithdrawal(100));
        denom_200.addActionListener(e -> handleWithdrawal(200));
        denom_500.addActionListener(e -> handleWithdrawal(500));
        denom_1000.addActionListener(e -> handleWithdrawal(1000));
        denom_2000.addActionListener(e -> handleWithdrawal(2000));
        denom_5000.addActionListener(e -> handleWithdrawal(5000));
        denom_10000.addActionListener(e -> handleWithdrawal(10000));

     
            
        setVisible(true); // Make frame visible after adding all components  
    }

    
    private void handleWithdrawal(int amount) {
    if (amount <= 0 || (amount % 100 != 0 && amount % 200 != 0 && amount % 500 != 0)) {
        JOptionPane.showMessageDialog(null, "Amount must be a positive multiple of 100, 200, or 500!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    waitingSeconds.setVisible(true);

    Timer timer = new Timer(2000, evt -> {
        waitingSeconds.setVisible(false);

        try (java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
                  Statement stmt = con.createStatement()) {

            String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage3";
            ResultSet rs = stmt.executeQuery(getCountIdQuery);
            int latestId = 0;

            if (rs.next()) {
                latestId = rs.getInt("max_id");
            }

            String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getBalanceQuery);

            double currentBalance = 0.0;
            if (rs.next()) {
                currentBalance = rs.getDouble("Balance");
            }

            if (currentBalance >= amount) {
                double newBalance = currentBalance - amount;

                String updateBalanceQuery = "UPDATE accountPage3 SET Balance = " + newBalance + " WHERE id = " + latestId;
                stmt.executeUpdate(updateBalanceQuery);

                JOptionPane.showMessageDialog(null, " ✅  Rs: " + amount + " successfully withdrawn!", "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
                //dispose(); // frame.setVisible(false);
               //  ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++ >>>
                // ✅  Insert into mini statement
                LocalDateTime nowDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = nowDateTime.format(formatter);

                try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059")) {
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
                        pstmt4.setString(2, Name+" FastCash (Withdrawn) ");
                        pstmt4.setDouble(3, amount);
                        pstmt4.setNull(4, java.sql.Types.DOUBLE); // null
                        pstmt4.setDouble(5, newBalance);
                        pstmt4.executeUpdate();
                    }

                } catch (SQLException miniEx) {
                    miniEx.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Mini statement insert error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // <<<  ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++
                
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient Balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error accessing database!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        ((Timer) evt.getSource()).stop();
    });

    timer.setRepeats(false);
    timer.start();
}

    
    // Method to create styled buttons
    private JButton createStyledFastCashButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded rectangle background
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border needed
            }
        };
        /*
       button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                content_pane.add(waitingSeconds);
                content_pane.repaint();
                content_pane.revalidate();

                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        content_pane.remove(waitingSeconds);
                        content_pane.repaint();
                        JOptionPane.showMessageDialog(null, text + " Successfully Withdrawn!", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        */
        // Hover effect for button1
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.BLACK);
                button.setBackground(new Color(192,192,192));
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(88,84,84));
                button.setBackground(new Color(220, 218,218));
            }
        });

        // Set button properties
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(new Color(88,84,84));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        
        return button;
    }
    
     // Method to create Submit styled buttons
    private static JButton createStyledSubmitButton(String text) {
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
        //button.setMargin(new Insets(6, 13, 6, 13)); // Adjust padding
        
        return button;
        
        
    }
    public static void main(String[] args) {
        new fastcashPage();
    }
}

