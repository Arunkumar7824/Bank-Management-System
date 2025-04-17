/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

class userATM extends JFrame{
    
    JLabel accNum, accNumResul, accHolderNameLabel, accHolderName;
    JButton deposit, withdrawal, fastcash, miniStateMent, changePin, balance, back;
    
    
      userATM(){
        super ("ATM");
        
        setSize(600, 700);
        setLocationRelativeTo(null); // Center the window
        // frameUserATM.setExtendedState(JFrame.MAXIMIZED_BOTH); // Start in maximized mode

        // Set background color
        getContentPane().setBackground(new Color(11, 139, 252)); // White
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        
        // West Panel
        JPanel border_W_Panel = new JPanel();
        border_W_Panel.setBounds(0, 0, 5, 660);
        border_W_Panel.setBackground(new Color(81, 142,247));
        add(border_W_Panel);
        
        // Borders
        JPanel border_N_Panel = new JPanel();
        border_N_Panel.setBounds(0, 0, 585, 40);
        border_N_Panel.setBackground(new Color(81, 142,247));
        add(border_N_Panel);
        
        JLabel side_Label = new JLabel("Automated Teller Machine");
        side_Label.setFont(new Font("Arial", Font.BOLD, 24));
        side_Label.setBounds(0, 0, 30, 40);
        side_Label.setForeground(Color.WHITE);
        border_N_Panel.add(side_Label);

        JPanel border_E_Panel = new JPanel();
        border_E_Panel.setBounds(580, 0, 5, 660);
        border_E_Panel.setBackground(new Color(81, 142,247));
        add(border_E_Panel);

        JPanel border_S_Panel = new JPanel();
        border_S_Panel.setBounds(0, 656, 585, 5);
        border_S_Panel.setBackground(new Color(81, 142,247));
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
        /*
       // String arun =super.getUserName;
        JLabel labelName = new JLabel("arun"+super.getUserName());
        
        //labelName.setText(super.getUserName());
        labelName.setBounds(100,50,150,150);
        labelName.setFont(new Font("Arial",Font.BOLD,22));
        labelName.setForeground(Color.GREEN);
*/
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,255,255));
        panel.setLayout(new GridBagLayout());
        
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        
       /*JPanel namePanel = new JPanel();
        namePanel.setBounds(100,100,400,100);
        namePanel.setBackground(Color.red);
        add(namePanel);*/
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
    Statement stmt = con.createStatement()) {

    // Get the latest account ID from accountPage1
    String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
    ResultSet rs = stmt.executeQuery(getCountIdQuery);
    int latestId = 0;
    if (rs.next()) {
        latestId = rs.getInt("max_id");
    }

    // Get the account holder's name using the latest ID
    String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = " + latestId;
    rs = stmt.executeQuery(getNameQuery);
    String accountHolderName = "";
    if (rs.next()) {
        accountHolderName = rs.getString("Name");
    }
    accHolderNameLabel = new JLabel("Name        : " + accountHolderName);

    // Get the latest CardNumber from accountPage3
    String getCardNumberQuery = "SELECT CardNumber FROM accountPage3 WHERE id = (SELECT MAX(id) FROM accountPage3)";
    rs = stmt.executeQuery(getCardNumberQuery);
    String cardNumber = "";
    if (rs.next()) {
        cardNumber = rs.getString("CardNumber");
    }
    accNum = new JLabel("Card Number : " + cardNumber);

} catch (SQLException e) {
    e.printStackTrace();
    accHolderNameLabel = new JLabel("Name: Error fetching name");
    accNum = new JLabel("Account Number: Error fetching number");
}

        //accHolderNameLabel = new JLabel("Name                  : "+getNameQuery);
        accHolderNameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        //accNum.setBorder(BorderFactory.createEmptyBorder(0, 20, 70, 20));
        /*
        accHolderName = new JLabel("arunkumar");
        accHolderName.setFont(new Font("Arial", Font.BOLD, 30));
        */
       //accNum = new JLabel("Account Number : ");
        accNum.setFont(new Font("Arial", Font.PLAIN, 25));
        //accNum.setBorder(BorderFactory.createEmptyBorder(0, 20, 70, 20));
        /*
        accNumResul = new JLabel("XXX");
        accNumResul.setFont(new Font("Arial", Font.BOLD, 30));
        */
        deposit = createStyledButtonsATM("DEPOSIT");
        deposit.setMargin(new Insets(6, 46, 6, 46));
        
        withdrawal = createStyledButtonsATM("WITHDRAW");
        withdrawal.setMargin(new Insets(6, 36, 6, 36));
        
        fastcash = createStyledButtonsATM("FASTCASH");
        fastcash.setMargin(new Insets(6, 38, 6, 38));
        
        miniStateMent = createStyledButtonsATM("MINI STATEMENT");
        miniStateMent.setMargin(new Insets(6, 16, 6, 16));
        
        changePin = createStyledButtonsATM("CHANGE PIN");
        changePin.setMargin(new Insets(6, 31, 6, 31));
        
        balance = createStyledButtonsATM("BALANCE");
        balance.setMargin(new Insets(6, 46, 6, 46));
        
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        panel.add(accHolderNameLabel,gbc);
        /*
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        panel.add(accHolderName,gbc);
        */
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(accNum,gbc);
        /*
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(accNumResul);
        */
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(deposit,gbc);
        
        gbc.gridx = 5;
        panel.add(withdrawal,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(fastcash,gbc);
        
        gbc.gridx = 5;
        panel.add(miniStateMent ,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(changePin,gbc);
        
        gbc.gridx = 5;
        panel.add(balance,gbc);
       
        
        
          // Click the deposit button
        deposit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new depositPage();
            }
        });
        
         // Click the withdraw button
        withdrawal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new withdrawalPage();
            }
        });
        
        // Click the deposit button
        fastcash.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new fastcashPage();
            }
        });
        
        // Click the changePin button
        changePin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new changePinPage();
            }
        });
                
        // Click the Mini Statement button
        miniStateMent.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               new miniStatementPage();
            }
        });
        
        // Click the Balance Enquiry button
        balance.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               new balanceEnquiryPage();
            }
        });
        
        add(panel);
        setVisible(true);
    }
    
    // Method to create styled buttons
    private static JButton createStyledButtonsATM(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded rectangle background
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 5, 5));

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
        button.setFont(new Font("Arial", Font.BOLD, 17));
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
        new userATM();
    }
}
