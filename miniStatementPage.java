
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.text.AbstractDocument;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author ARUNKUMAR
 */
class miniStatementPage extends JFrame {
    
    JPanel border_W_Panel, border_N_Panel, border_E_Panel, border_S_Panel,content_pane;
    JLabel accNum, above_Label, PinNumLabel, waitSeconds, dateLabel, timeLabel, atmIdLabel, atmId, dateTimeLabel, transactionDetails  ;
    JTextField PinNum_TextField ;
    JButton submitButton, back ;

    String accountHolderPinNumber = "";
    miniStatementPage(){
        super ("Mini Statement Page 💻");  
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
        above_Label = new JLabel("Mini Statement");
        above_Label.setFont(new Font("Arial", Font.BOLD, 24));
        above_Label.setBounds(200, 40, 260, 30); // Adjusted for better positioning
        above_Label.setForeground(Color.WHITE);
        border_N_Panel.add(above_Label);

        // ---------------- Content Panel ----------------//
        content_pane = new JPanel();
        content_pane.setBounds(0, 120, 580, 540);
        content_pane.setBackground(Color.WHITE);
        content_pane.setLayout(null);
        add(content_pane); 
       
        
        // Pin Number Label
        accNum = new JLabel("Card Number    : Loading...");
        accNum.setFont(new Font("Arial", Font.BOLD, 21));
        accNum.setForeground(Color.BLUE);
        accNum.setBounds(130, 80, 360, 40);
        content_pane.add(accNum);
        
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
    /*
    String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
    rs = stmt.executeQuery(getBalanceQuery);

    if (rs.next()) {
       currentBalance = rs.getDouble("Balance");
    }
*/
} catch (SQLException e) {
    e.printStackTrace();
    accNum.setText("Error fetching data");
}


        
        
        // --------------- After i click submit button -----------------
       
       // Date and Time
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = nowDateTime.format(formatter);

        // Labels
        dateLabel = createLabel("Date", 50, 50);
        timeLabel = createLabel("Time", 164, 50);
        atmIdLabel = createLabel("ATM ID", 380, 50);
        atmId = createLabel("AK382002", 380, 85);
        dateTimeLabel = createLabel(formattedDateTime, 50, 85);
        transactionDetails = createLabel("TRANSACTION DETAILS", 50, 200);
        transactionDetails.setFont(new Font("Arial", Font.BOLD, 22));

        content_pane.add(dateLabel);
        content_pane.add(timeLabel);
        content_pane.add(atmIdLabel);
        content_pane.add(atmId);
        content_pane.add(dateTimeLabel);
        content_pane.add(transactionDetails);
/*
        // Table Data
        String[][] data = {
                {"10/03/25", "CWDR/000000 D", "500.00"},
                {"15/03/25", "MPAY/UPI/TR D", "2000.00"},
                {"15/03/25", "MPAY/UPI/TR D", "300.00"},
                {"17/03/25", "MPAY/UPI/TR C", "1000.00"},
                {"18/03/25", "CWDR/746382 C", "9000.00"},
                {"18/03/25","CWDR/746382 C","9000.00"},
                {"18/03/25","CWDR/746382 C","9000.00"},
                {"18/03/25","CWDR/746382 C","9000.00"},
                {"18/03/25","CWDR/746382 C","9000.00"},
                {"18/03/25","CWDR/746382 C","9000.00"},
                {"","","AVAIL BAL +000000103.43"}
        };
        String[] column = {"Date", "Particulars", "Debit", "Credit", "Balance"};

        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(50, 245, 480, 200);
        sp.setVisible(false);
        content_pane.add(sp);
*/

        
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
                    PinNum_TextField.setText("");
                    waitSeconds.setVisible(false);
                    accNum.setVisible(false);
                    
                    JOptionPane.showMessageDialog(null, " ✅  PIN Validated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    // +++++++++++++++++++++++++
                    try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
    
    
    // Get latest ID from accountPage1
                    int accountId = 0;
                    String getIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
                    try (PreparedStatement pstmt1 = con.prepareStatement(getIdQuery);
                         ResultSet rs1 = pstmt1.executeQuery()) {
                        if (rs1.next()) {
                            accountId = rs1.getInt("max_id");
                        }
                    }
     // Get Name
                    String Name = "";
                    String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = ?";
                    try (PreparedStatement pstmt2 = con.prepareStatement(getNameQuery)) {
                        pstmt2.setInt(1, accountId);
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            if (rs2.next()) {
                                Name = rs2.getString("Name");
                            } else {
                                Name = "Unknown";
                            }
                        }
                    }

    String tableName = Name+"_miniStatement"; // or dynamically use Name + "_miniStatement"
    String query = "SELECT * FROM " + tableName;
    
    PreparedStatement pst = con.prepareStatement(query);
    ResultSet rs = pst.executeQuery();

    // Convert ResultSet to TableModel
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    // Column names
    Vector<String> columnNames = new Vector<>();
    for (int i = 1; i <= columnCount; i++) {
        columnNames.add(metaData.getColumnName(i));
    }

    // Data
    Vector<Vector<Object>> data = new Vector<>();
    while (rs.next()) {
        Vector<Object> row = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            row.add(rs.getObject(i));
        }
        data.add(row);
    }

    // JTable and scroll pane
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(11, 245, 565, 200);
        scrollPane.setVisible(true);
        content_pane.add(scrollPane);

} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error loading mini statement!", "Error", JOptionPane.ERROR_MESSAGE);
}

                    //++++++++++++++++++++++++++++++++++=
                    PinNumLabel.setVisible(false);
                    PinNum_TextField.setVisible(false);
                    submitButton.setVisible(false);
                     
                    dateLabel.setVisible(true);
                    timeLabel.setVisible(true);
                    atmIdLabel.setVisible(true);
                    atmId.setVisible(true);
                    dateTimeLabel.setVisible(true);
                    transactionDetails.setVisible(true);
                    //scrollPane.setVisible(true);
                }
            }.execute();
        });
        
        
        setVisible(true); // Make frame visible after adding all components 
    } 
    
     // Method to create a JLabel
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 22));
        label.setBounds(x, y, 300, 30);
        label.setVisible(false);
        return label;
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
    
    public static void main (String args[]){
        new miniStatementPage();
    }
}    

