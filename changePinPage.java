
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.text.AbstractDocument;

class changePinPage extends JFrame {
    JLabel  side_Label, name_Label, accNum, PinNumLabel, waitSeconds, textDisableLabel, textEnableLabel, NewPinNumLabel, ReEnter_NewPinNumLabel, PinChange_waitSeconds;
    JTextField PinNum_TextField, NewPinNum_TextField, ReEnter_NewPinNum_TextField;
    JPanel border_W_Panel, border_N_Panel, border_E_Panel, border_S_Panel, content_pane, center_Panel;
    JButton submitButton, PinChangeButton, back;
    
    String accountHolderPinNumber = "";
    
    changePinPage() {
        super ("change Pin Page");
        setSize(600, 700);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // Use absolute positioning

        // West Panel
        border_W_Panel = new JPanel();
        border_W_Panel.setBounds(0, 0, 200, 660);
        border_W_Panel.setBackground(Color.BLUE);
        border_W_Panel.setLayout(null);

        side_Label = new JLabel("ChangePin");
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
        
        // ----------------Above Content --------------- //
        content_pane = new JPanel();
        content_pane.setBounds(200, 0, 380, 660);
        content_pane.setBackground(Color.WHITE);
        content_pane.setLayout(null);
        
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

            //Name
            String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = " + latestId;
            rs = stmt.executeQuery(getNameQuery);

            String Name = rs.next() ? rs.getString("Name") : "Error fetching card number";
            name_Label = new JLabel("Name               : " + Name);
            
            //Card Number
            String getCardNumberQuery = "SELECT CardNumber FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getCardNumberQuery);

            String cardNumber = rs.next() ? rs.getString("CardNumber") : "Error fetching card number";
            accNum = new JLabel("Card Number    : " + cardNumber);
            
            // Balance 
            String getBalanceQuery = "SELECT Balance FROM accountPage3 WHERE id = " + latestId;
            rs = stmt.executeQuery(getBalanceQuery);

           
/*
            if (rs.next()) {
               currentBalance = rs.getDouble("Balance");
            }
*/
        } catch (SQLException e) {
            e.printStackTrace();
            accNum = new JLabel("Error fetching data");
        }
        
        //name_Label = new JLabel("Arunkumar");
        name_Label.setFont(new Font("Arial", Font.PLAIN, 21));
        name_Label.setForeground(Color.BLUE);
        name_Label.setBounds(30,60,400,40);
        content_pane.add(name_Label);
        
        //accNum = new JLabel("Acc Num : "+"XXXXX");
        accNum.setFont(new Font("Arial", Font.PLAIN, 21));
        accNum.setForeground(Color.BLUE);
        accNum.setBounds(30,100,300,40);
        content_pane.add(accNum);
        
        // Pin Number Label
        PinNumLabel = new JLabel("PIN                   :");
        PinNumLabel.setForeground(Color.BLACK);
        PinNumLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        PinNumLabel.setBounds(30, 160, 170, 30);
        content_pane.add(PinNumLabel);

        // Pin Number Field
        PinNum_TextField = new JTextField(15);
        PinNum_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        PinNum_TextField.setBounds(190, 160, 160, 28);
        PinNum_TextField.setFocusable(true);
        PinNum_TextField.setBackground(Color.WHITE);
        content_pane.add(PinNum_TextField);
       
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) PinNum_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        
        // submit Button
        submitButton = createStyledSubmitButton("Submit");
        submitButton.setMargin(new Insets(6, 32, 6, 32));
        submitButton.setBounds(110,220,160,38);
        content_pane.add(submitButton);
        
        
        
        waitSeconds = new JLabel("Please Wait ...");
        waitSeconds.setBounds(120, 275, 270, 30);
        waitSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        waitSeconds.setForeground( new Color(253,170,62));
        waitSeconds.setVisible(false);
        content_pane.add(waitSeconds);
        
        // ---------------- center panel (color) -----------------//
        center_Panel = new JPanel();
        center_Panel.setBounds(15,320,355,1);
        center_Panel.setBackground(Color.BLUE);
        content_pane.add(center_Panel);
        
        textDisableLabel = new JLabel("Disable Mode");
        textDisableLabel.setBounds(120, 340, 270, 30);
        textDisableLabel.setFont(new Font("Arial", Font.BOLD, 22));
        textDisableLabel.setForeground( new Color(253,170,62));
        textDisableLabel.setVisible(true);
        content_pane.add(textDisableLabel);
        
        textEnableLabel = new JLabel("Enter New Pin");
        textEnableLabel.setBounds(120, 340, 270, 30);
        textEnableLabel.setFont(new Font("Arial", Font.BOLD, 22));
        textEnableLabel.setForeground( new Color(19, 86,243));
        textEnableLabel.setVisible(false);
        content_pane.add(textEnableLabel);
        
         // New Pin Number Label
        NewPinNumLabel = new JLabel("New PIN          :");
        NewPinNumLabel.setForeground(Color.BLACK);
        NewPinNumLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        NewPinNumLabel.setBounds(30, 415, 170, 30);
        NewPinNumLabel.setEnabled(false); // Completely disable
        content_pane.add(NewPinNumLabel);

        // New Pin Number Field
        NewPinNum_TextField = new JTextField(15);
        NewPinNum_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        NewPinNum_TextField.setBounds(190, 415, 160, 28);
        NewPinNum_TextField.setFocusable(true);
        NewPinNum_TextField.setBackground(Color.WHITE);
        NewPinNum_TextField.setEnabled(false); // Completely disable
        content_pane.add(NewPinNum_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) NewPinNum_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        
        //Re-Enter New Pin Number Label
        ReEnter_NewPinNumLabel = new JLabel("Re-Enter          :");
        ReEnter_NewPinNumLabel.setForeground(Color.BLACK);
        ReEnter_NewPinNumLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        ReEnter_NewPinNumLabel.setBounds(30, 465, 170, 30);
        ReEnter_NewPinNumLabel.setEnabled(false); // Completely disable
        content_pane.add(ReEnter_NewPinNumLabel);

        //Re-Enter New Pin Number Field
        ReEnter_NewPinNum_TextField = new JTextField(15);
        ReEnter_NewPinNum_TextField.setFont(new Font("Arial", Font.PLAIN, 16));
        ReEnter_NewPinNum_TextField.setBounds(190, 465, 160, 28);
        ReEnter_NewPinNum_TextField.setFocusable(true);
        ReEnter_NewPinNum_TextField.setBackground(Color.WHITE);
        ReEnter_NewPinNum_TextField.setEnabled(false); // Completely disable
        content_pane.add(ReEnter_NewPinNum_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) ReEnter_NewPinNum_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

         // change Button
        PinChangeButton = createStyledPinChangeButton("Change");
        PinChangeButton.setMargin(new Insets(6, 32, 6, 32));
        PinChangeButton.setBounds(110,525,160,38);
        PinChangeButton.setEnabled(false);
        content_pane.add(PinChangeButton);
        
        PinChange_waitSeconds = new JLabel("Please Wait ...");
        PinChange_waitSeconds.setBounds(120, 595, 270, 30);
        PinChange_waitSeconds.setFont(new Font("Arial", Font.ITALIC, 22));
        PinChange_waitSeconds.setForeground( new Color(253,170,62));
        PinChange_waitSeconds.setVisible(false);
        content_pane.add(PinChange_waitSeconds);
        
      

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
                    
                    JOptionPane.showMessageDialog(null, " ✅  PIN Validated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    textEnableLabel.setVisible(true);
                    
                    NewPinNumLabel.setEnabled(true); // Completely disable
                    NewPinNum_TextField.setEnabled(true);
                    
                    ReEnter_NewPinNumLabel.setEnabled(true); // Completely disable
                    ReEnter_NewPinNum_TextField.setEnabled(true);
                    
                    
                    textDisableLabel.setVisible(false);
                    PinChangeButton.setEnabled(true);
                }
            }.execute();
        });
        
        PinChangeButton.addActionListener(e -> {
    String enteredNewPin = NewPinNum_TextField.getText().trim();
    String reEnteredNewPin = ReEnter_NewPinNum_TextField.getText().trim();

    // Basic validation
    if (enteredNewPin.isEmpty()) {
        NewPinNum_TextField.setText("");
        ReEnter_NewPinNum_TextField.setText("");
        JOptionPane.showMessageDialog(null, "Please enter a PIN!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (reEnteredNewPin.isEmpty()) {
        NewPinNum_TextField.setText("");
        ReEnter_NewPinNum_TextField.setText("");
        JOptionPane.showMessageDialog(null, "Please re-enter the PIN!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!enteredNewPin.equals(reEnteredNewPin)) {
        NewPinNum_TextField.setText("");
        ReEnter_NewPinNum_TextField.setText("");
        JOptionPane.showMessageDialog(null, "PINs do not match! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Show loading indicator
    PinChange_waitSeconds.setVisible(true);

    // Background thread
    new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            Thread.sleep(2000); // simulate waiting
            return null;
        }

        @Override
        protected void done() {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059")) {
                // Get the latest ID
                String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage3";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(getCountIdQuery)) {

                    int latestId = 0;
                    if (rs.next()) {
                        latestId = rs.getInt("max_id");
                    }

                    // Update pin securely using PreparedStatement
                    String updatePinQuery = "UPDATE accountPage3 SET PinNumber = ? WHERE id = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(updatePinQuery)) {
                        pstmt.setString(1, enteredNewPin);
                        pstmt.setInt(2, latestId);
                        int rowsUpdated = pstmt.executeUpdate();

                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, " ✅  PIN Changed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No account found to update.", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error accessing database!", "Database Error", JOptionPane.ERROR_MESSAGE);
            }

            // Reset UI
            NewPinNum_TextField.setText("");
            ReEnter_NewPinNum_TextField.setText("");
            PinChange_waitSeconds.setVisible(false);
        }
    }.execute();
});

        setVisible(true);
        add(content_pane);
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
    
    // Method to create styled buttons
    private static JButton createStyledPinChangeButton(String text) {
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
        new changePinPage();
    }
}
