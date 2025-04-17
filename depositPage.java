
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.text.AbstractDocument;


class depositPage extends JFrame {
    JLabel denom_Label, denomCount_Label, totalAmountLabel, Amount_Label, waitSeconds;
    JTextField denomCount_TextField, Amount_TextField;
    JButton add_button, depositButton, back;
    
    
    private int totalAmount = 0; // Class-level variable to retain accumulated value
    Double currentBalance = 00.00;
   


    depositPage() {
        super ("Deposit Page ");
        setSize(600, 700);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // Use absolute positioning

        // West Panel
        JPanel border_W_Panel = new JPanel();
        border_W_Panel.setBounds(0, 0, 200, 660);
        border_W_Panel.setBackground(Color.BLUE);
        border_W_Panel.setLayout(null);

        JLabel side_Label = new JLabel("Deposit");
        side_Label.setFont(new Font("Arial", Font.BOLD, 24));
        side_Label.setBounds(51, 300, 200, 40);
        side_Label.setForeground(Color.WHITE);

        border_W_Panel.add(side_Label);
        add(border_W_Panel);
        
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

        // Borders
        JPanel border_N_Panel = new JPanel();
        border_N_Panel.setBounds(0, 0, 585, 5);
        border_N_Panel.setBackground(Color.BLUE);
        add(border_N_Panel);

        JPanel border_E_Panel = new JPanel();
        border_E_Panel.setBounds(580, 0, 5, 660);
        border_E_Panel.setBackground(Color.BLUE);
        add(border_E_Panel);

        JPanel border_S_Panel = new JPanel();
        border_S_Panel.setBounds(0, 656, 585, 5);
        border_S_Panel.setBackground(Color.BLUE);
        add(border_S_Panel);

        // ----------------Above Content --------------- //
        JPanel content_pane = new JPanel();
        content_pane.setBounds(200, 0, 380, 300);
        content_pane.setBackground(Color.WHITE);
        content_pane.setLayout(null);

        // Denomination Label
        denom_Label = new JLabel("Denom            :");
        denom_Label.setForeground(Color.BLACK);
        denom_Label.setFont(new Font("Arial", Font.PLAIN, 21));
        denom_Label.setBounds(30, 45, 170, 30);
        content_pane.add(denom_Label);

        // Denomination Dropdown
        Integer[] dnom_Items = {500, 200, 100, 50, 20, 10};
        JComboBox<Integer> denom_comboBox = new JComboBox<>(dnom_Items);
        denom_comboBox.setBounds(190, 45, 160, 29);
        denom_comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        content_pane.add(denom_comboBox);

        // Denomination Count Label
        denomCount_Label = new JLabel("Denom Count  :");
        denomCount_Label.setForeground(Color.BLACK);
        denomCount_Label.setFont(new Font("Arial", Font.PLAIN, 21));
        denomCount_Label.setBounds(30, 95, 170, 30);
        content_pane.add(denomCount_Label);

        // Denomination Count Text Field
        denomCount_TextField = new JTextField(15);
        denomCount_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        denomCount_TextField.setBounds(190, 95, 160, 28);
        denomCount_TextField.setFocusable(true);
        denomCount_TextField.setBackground(Color.WHITE);
        content_pane.add(denomCount_TextField);

        // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) denomCount_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Total Amount Label
        totalAmountLabel = new JLabel("Total Amount: $ 0");
        totalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        totalAmountLabel.setForeground(Color.BLACK);
        totalAmountLabel.setBounds(80, 210, 300, 30);
        content_pane.add(totalAmountLabel);

        // Add Button
        add_button = createStyledAddButton("add");
        add_button.setBounds(285, 140, 60, 30);
        content_pane.add(add_button);

        // Action Listener for Add Button
        add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int denom = (Integer) denom_comboBox.getSelectedItem();
                    int denomCount = Integer.parseInt(denomCount_TextField.getText());

                    // Accumulate total amount
                    totalAmount += denom * denomCount;
                    

                    // Update Total Amount Label
                    totalAmountLabel.setText("Total Amount: $ " + totalAmount);
                    
                    denomCount_TextField.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"Invalid input! Enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ---------------- center panel (color) -----------------//
        JPanel center_Panel = new JPanel();
        center_Panel.setBounds(15,0,350,1);
        center_Panel.setBackground(Color.BLUE);
        
        //=============
      // Declare this at the top of your class, outside any method.
       Double depositBalance = 0.00;

try (java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
     Statement stmt = con.createStatement()) {

    String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
    ResultSet rs = stmt.executeQuery(getCountIdQuery);
    int latestId = 0;

    if (rs.next()) {
        latestId = rs.getInt("max_id");
    }

    // 1. Fetch the current balance first
    double currentBalance = 0.00;
    String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
    rs = stmt.executeQuery(getBalanceQuery);

    if (rs.next()) {
        currentBalance = rs.getDouble("Balance");
    }

    // 2. Now calculate the new balance correctly
    double CDBalance = currentBalance + totalAmount;

    // 3. Update the balance in the database
    String updateBalanceQuery = "UPDATE accountPage3 SET Balance = " + CDBalance + " WHERE id = " + latestId;
    stmt.executeUpdate(updateBalanceQuery);

    // 4. Retrieve the updated balance
    rs = stmt.executeQuery(getBalanceQuery);
    if (rs.next()) {
        depositBalance = rs.getDouble("Balance");
    }

    // (Optional) Print for debugging
    System.out.println("Previous Balance: " + currentBalance);
    System.out.println("Deposit Amount: " + totalAmount);
    System.out.println("New Balance: " + depositBalance);

} catch (SQLException e) {
    e.printStackTrace();
}
     // ---------------- Below Content --------------- //
        JPanel below_Panel = new JPanel();
        below_Panel.setBounds(200, 300, 380, 360);
        below_Panel.setBackground(Color.WHITE);
        below_Panel.setLayout(null);
        
        below_Panel.add(center_Panel);

        Amount_Label = new JLabel("Enter Amount   : ");
        Amount_Label.setForeground(Color.BLACK);
        Amount_Label.setFont(new Font("Arial", Font.PLAIN, 21));
        Amount_Label.setBounds(30, 95, 170, 30);
        below_Panel.add(Amount_Label);
        
        // Denomination Count Text Field
        Amount_TextField = new JTextField(15);
        Amount_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        Amount_TextField.setBounds(190, 95, 160, 28);
        Amount_TextField.setFocusable(true);
        Amount_TextField.setBackground(Color.WHITE);
        
        below_Panel.add(Amount_TextField);
        
        // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) Amount_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        
        // Deposite Button
        depositButton = createStyledDepositButton("DEPOSIT");
        depositButton.setMargin(new Insets(6, 32, 6, 32));
        depositButton.setBounds(110,175,160,38);
        below_Panel.add(depositButton);
        
        waitSeconds = new JLabel("Please Wait ...");
        waitSeconds.setBounds(120, 240, 250, 45);
        waitSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        waitSeconds.setForeground( new Color(253,170,62));
        waitSeconds.setVisible(false);
        below_Panel.add(waitSeconds);

        // Deposite button action 
        // Deposite button action 
depositButton.addActionListener(e -> {
    String amountText = Amount_TextField.getText();
    if (amountText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter an amount!", "Deposit Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (totalAmount != Integer.parseInt(amountText)) {
        Amount_TextField.setText("");
        JOptionPane.showMessageDialog(null, "Please enter the correct amount!", "Deposit Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    waitSeconds.setVisible(true);
    below_Panel.repaint();

    new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            Thread.sleep(3000); // Simulating delay
            return null;
        }

        @Override
        protected void done() {
            waitSeconds.setVisible(false);

            try (java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
                 Statement stmt = con.createStatement()) {

                // Fetch the latest account ID
                String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
                ResultSet rs = stmt.executeQuery(getCountIdQuery);
                int latestId = 0;

                if (rs.next()) {
                    latestId = rs.getInt("max_id");
                }

                // Fetch the current balance from accountPage3
                String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
                rs = stmt.executeQuery(getBalanceQuery);

                if (rs.next()) {
                    currentBalance = rs.getDouble("Balance");
                }

                // Add the deposited amount to the current balance
                double newBalance = currentBalance + totalAmount;

                // Update the balance in accountPage3 table
                String updateBalanceQuery = "UPDATE accountPage3 SET Balance = " + newBalance + " WHERE id = " + latestId;
                stmt.executeUpdate(updateBalanceQuery);

                JOptionPane.showMessageDialog(null, " ✅  Rs: " + totalAmount + " Successfully Deposited! ", "Deposit", JOptionPane.INFORMATION_MESSAGE);

                // ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++ >>>
                LocalDateTime nowDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = nowDateTime.format(formatter);

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059")) {

                    // Step 1: Get the latest account ID
                    int latestIdInner = 0;
                    String getCountIdQueryInner = "SELECT MAX(id) AS max_id FROM accountPage1";
                    try (PreparedStatement pstmt1 = conn.prepareStatement(getCountIdQueryInner);
                         ResultSet rs1 = pstmt1.executeQuery()) {
                        if (rs1.next()) {
                            latestIdInner = rs1.getInt("max_id");
                        }
                    }

                    // Step 2: Get the Name
                    String Name = "";
                    String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = ?";
                    try (PreparedStatement pstmt2 = conn.prepareStatement(getNameQuery)) {
                        pstmt2.setInt(1, latestIdInner);
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            if (rs2.next()) {
                                Name = rs2.getString("Name");
                            } else {
                                Name = "Unknown";
                            }
                        }
                    }

                    // Step 3: Insert into MiniStatement table
                    String insertQuery = "INSERT INTO " + Name + "_miniStatement (DATE, PARTICULARS, DEBIT, CREDIT, BALANCE) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt4 = conn.prepareStatement(insertQuery)) {
                        pstmt4.setString(1, formattedDateTime);
                        pstmt4.setString(2, Name + " Deposited");
                        pstmt4.setNull(3, java.sql.Types.DOUBLE); // DEBIT = null
                        pstmt4.setDouble(4, totalAmount); // CREDIT = deposit
                        pstmt4.setDouble(5, newBalance); // Updated balance
                        pstmt4.executeUpdate();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // <<<< ++++++++++++++++++++++++++++ mini statement +++++++++++++++++++++++

                // Reset totalAmount and update label
                totalAmount = 0;
                totalAmountLabel.setText("Total Amount: ₹ " + totalAmount);
                Amount_TextField.setText("");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating balance in database!", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }.execute();
});

        add(content_pane);
        add(below_Panel);
        setVisible(true);
    }

    // Styled Button Creation
    private static JButton createStyledAddButton(String text) {
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

        // Hover Effect
        button.addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(255, 197, 4));
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.DARK_GRAY);
                button.setBackground(new Color(232, 192, 61));
            }
        });

        // Button Styling
        button.setFont(new Font("Arial", Font.PLAIN, 21));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(232, 192, 61));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(8, 9, 8, 9));

        return button;
    }
    
    // Method to create styled buttons
    private static JButton createStyledDepositButton(String text) {
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
    
    public static void main(String[] args){
        new depositPage();
    }
}

