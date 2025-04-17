
package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class createAccount_Page3 extends JFrame implements ActionListener{
    // Randomly generate a 4-digit number for the card
    Random random = new Random();
    long first4 = (Math.abs(random.nextLong()) % 9000L) + 1000L; 
    String first = String.valueOf(first4);
    
    long first_4 = (Math.abs(random.nextLong()) % 9000L) + 1000L; 
    String second = String.valueOf(first_4);

    // Declare components
    JLabel page_Label, personal_De, accountType_Label, cardNoTitle_Label, cardNumber_Label,
           cardNoTitleContent_Label, cardNumberContent_Label, pin_Label, pinNumber_Label, 
           pinContent_Label, servicesReq_Label;
           
    JRadioButton savingAcc_radioButton, fixedDepositAcc_radioButton, 
                 currentAcc_radioButton, recurringDepositAcc_radioButton;
                 
    ButtonGroup accType_buttonGroup;
    
    JCheckBox atmCard_checkBox, internetBank_checkBox, mobileBank_checkBox, 
             emailAlerts_checkBox, chequeBook_checkBox, statement_checkBox, declare_checkBox;
             
    JButton back, submit, cancel;
    
    
    createAccount_Page3(){
        
         // Create Frame
        super ("Application Form");
        setSize(850, 700);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null);
        
        // Create Panel
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 850, 700);
        contentPane.setBackground(new Color(222, 255, 228));
        contentPane.setLayout(null);
        
/*
        // Load Image
        String imagePath = "C:\\Users\\ARUNKUMAR\\Documents\\NetBeansProjects\\AtmSystem\\src\\main\\java\\AtmImage.png";
        JLabel imageLabel = createImageLabel(imagePath, 100, 100);
        imageLabel.setBounds(10, 10, 100, 100);
        contentPane.add(imageLabel);
*/
        // Labels
        page_Label = new JLabel("Page 3");
        page_Label.setFont(new Font("Raleway", Font.BOLD, 19));
        page_Label.setBounds(658, 50, 100, 30);
        contentPane.add(page_Label);

        personal_De = new JLabel("Account Details");
        personal_De.setFont(new Font("Raleway", Font.BOLD, 19));
        personal_De.setBounds(350, 50, 200, 30);
        contentPane.add(personal_De);

        // Line Panel
        JPanel line_Panel = new JPanel();
        line_Panel.setBounds(110, 90, 610, 1);
        line_Panel.setBackground(Color.BLUE);
        contentPane.add(line_Panel);

        // Account Type Section
        accountType_Label = new JLabel("Account Type:");
        accountType_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        accountType_Label.setBounds(110, 100, 300, 30);
        contentPane.add(accountType_Label);

        
        String serviceOptions_RadioB1 = "Saving Account";
        String serviceOptions_RadioB2 = "Fixed Deposit Account";
        String serviceOptions_RadioB3 = "Current Account";
        String serviceOptions_RadioB4 = "Recurring Deposit Account";
        
        savingAcc_radioButton = new JRadioButton(serviceOptions_RadioB1);
        savingAcc_radioButton.setFont(new Font("Raleway", Font.PLAIN, 14));
        savingAcc_radioButton.setBackground(new Color(222, 255, 228));
        savingAcc_radioButton.setBounds(180, 140, 200, 30);
        contentPane.add(savingAcc_radioButton);
            
        fixedDepositAcc_radioButton = new JRadioButton(serviceOptions_RadioB2);
        fixedDepositAcc_radioButton.setFont(new Font("Raleway", Font.PLAIN, 14));
        fixedDepositAcc_radioButton.setBackground(new Color(222, 255, 228));
        fixedDepositAcc_radioButton.setBounds(380, 140, 200, 30);
        contentPane.add(fixedDepositAcc_radioButton);
        
        currentAcc_radioButton = new JRadioButton(serviceOptions_RadioB3);
        currentAcc_radioButton.setFont(new Font("Raleway", Font.PLAIN, 14));
        currentAcc_radioButton.setBackground(new Color(222, 255, 228));
        currentAcc_radioButton.setBounds(180, 180, 200, 30);
        contentPane.add(currentAcc_radioButton);
        
        recurringDepositAcc_radioButton = new JRadioButton(serviceOptions_RadioB4);
        recurringDepositAcc_radioButton.setFont(new Font("Raleway", Font.PLAIN, 14));
        recurringDepositAcc_radioButton.setBackground(new Color(222, 255, 228));
        recurringDepositAcc_radioButton.setBounds(380, 180, 200, 30);
        contentPane.add(recurringDepositAcc_radioButton);
        
        // Create ButtonGroup and add radio buttons to it
       accType_buttonGroup = new ButtonGroup();
       accType_buttonGroup.add(savingAcc_radioButton);
       accType_buttonGroup.add(fixedDepositAcc_radioButton);
       accType_buttonGroup.add(currentAcc_radioButton);
       accType_buttonGroup.add(recurringDepositAcc_radioButton);
       
/*
        JRadioButton[] radioButtons = {savingAcc_radioButton, fixedDepositAcc_radioButton, currentAcc_radioButton, recurringDepositAcc_radioButton};
        String[] serviceOptions_RadioB = {"Saving Account", "Fixed Deposit Account", "Current Account", "Recurring Deposit Account"};
        
        int yPosition = 140;
        for (int i = 0; i < radioButtons.length; i++) {
            
            radioButtons[i] = new JRadioButton(serviceOptions_RadioB[i]);
            radioButtons[i].setFont(new Font("Raleway", Font.PLAIN, 14));
            radioButtons[i].setBackground(new Color(222, 255, 228));
            radioButtons[i].setBounds((i % 2 == 0) ? 180 : 380, yPosition, 200, 30);
            contentPane.add(radioButtons[i]);
            if (i % 2 != 0) yPosition += 40;
        }
       // Create ButtonGroup and add radio buttons to it
       accType_buttonGroup = new ButtonGroup();
       for (JRadioButton radioButton : radioButtons) {
       accType_buttonGroup.add(radioButton);
       }
*/
       
        // Card Details Section
        cardNoTitle_Label = new JLabel("Card Number:");
        cardNoTitle_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        cardNoTitle_Label.setBounds(110, 230, 200, 30);
        contentPane.add(cardNoTitle_Label);

        cardNumber_Label = new JLabel("XXXX  XXXX  XXXX  " + first);
        cardNumber_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        cardNumber_Label.setBounds(340, 230, 300, 30);
        contentPane.add(cardNumber_Label);

        cardNoTitleContent_Label = new JLabel("(Your 16-digit Card Number)");
        cardNoTitleContent_Label.setFont(new Font("Raleway", Font.PLAIN, 12));
        cardNoTitleContent_Label.setBounds(110, 260, 350, 30);
        contentPane.add(cardNoTitleContent_Label);

        cardNumberContent_Label = new JLabel("It appears on ATM Card, Cheque Book, and Statements.");
        cardNumberContent_Label.setFont(new Font("Raleway", Font.PLAIN, 12));
        cardNumberContent_Label.setBounds(340, 260, 450, 30);
        contentPane.add(cardNumberContent_Label);

        // PIN Section
        pin_Label = new JLabel("PIN:");
        pin_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        pin_Label.setBounds(110, 310, 450, 30);
        contentPane.add(pin_Label);

        pinNumber_Label = new JLabel(second);
        pinNumber_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        pinNumber_Label.setForeground(Color.BLUE);
        pinNumber_Label.setBounds(340, 310, 100, 30);
        contentPane.add(pinNumber_Label);

        pinContent_Label = new JLabel("(4-digit password)");
        pinContent_Label.setFont(new Font("Raleway", Font.PLAIN, 13));
        pinContent_Label.setBounds(110, 340, 450, 30);
        contentPane.add(pinContent_Label);

        // Services Required Section
        servicesReq_Label = new JLabel("Services Required:");
        servicesReq_Label.setFont(new Font("Raleway", Font.BOLD, 18));
        servicesReq_Label.setBounds(110, 380, 200, 30);
        contentPane.add(servicesReq_Label);
        
        String serviceOptions_CheckB1 = "ATM Card";
        String internetBank_checkB2 = "Internet Banking";
        String mobileBank_checkB3 = "Mobile Banking";
        String emailAlerts_checkB4 = "EMAIL Alerts";
        String chequeBook_checkB5 = "Cheque Book";
        String statement_checkB6 = "E-Statement";
        
        atmCard_checkBox = new JCheckBox(serviceOptions_CheckB1);
        atmCard_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        atmCard_checkBox.setBackground(new Color(222, 255, 228));
        atmCard_checkBox.setBounds(180, 420, 150, 30);
        contentPane.add(atmCard_checkBox);
        
        internetBank_checkBox = new JCheckBox(internetBank_checkB2);
        internetBank_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        internetBank_checkBox.setBackground(new Color(222, 255, 228));
        internetBank_checkBox.setBounds(380, 420, 150, 30);
        contentPane.add(internetBank_checkBox);
       
        mobileBank_checkBox = new JCheckBox(mobileBank_checkB3);
        mobileBank_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        mobileBank_checkBox.setBackground(new Color(222, 255, 228));
        mobileBank_checkBox.setBounds(180, 460, 150, 30);
        contentPane.add(mobileBank_checkBox);
        
        emailAlerts_checkBox = new JCheckBox(emailAlerts_checkB4);
        emailAlerts_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        emailAlerts_checkBox.setBackground(new Color(222, 255, 228));
        emailAlerts_checkBox.setBounds(380, 460, 150, 30);
        contentPane.add(emailAlerts_checkBox);
        
        chequeBook_checkBox = new JCheckBox(chequeBook_checkB5);
        chequeBook_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        chequeBook_checkBox.setBackground(new Color(222, 255, 228));
        chequeBook_checkBox.setBounds(180, 500, 150, 30);
        contentPane.add(chequeBook_checkBox);
        
        statement_checkBox = new JCheckBox(statement_checkB6);
        statement_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        statement_checkBox.setBackground(new Color(222, 255, 228));
        statement_checkBox.setBounds(380, 500, 150, 30);
        contentPane.add(statement_checkBox);
/*
        String[] serviceOptions_CheckB = {"ATM Card", "Internet Banking", "Mobile Banking", "EMAIL Alerts", "Cheque Book", "E-Statement"};
        JCheckBox[] serviceCheckBoxes = {atmCard_checkBox, internetBank_checkBox, mobileBank_checkBox, emailAlerts_checkBox, chequeBook_checkBox, statement_checkBox};
        
        int yCheck = 420;
        for (int i = 0; i < serviceCheckBoxes.length; i++) {
            serviceCheckBoxes[i] = new JCheckBox(serviceOptions_CheckB[i]);
            serviceCheckBoxes[i].setFont(new Font("Raleway", Font.PLAIN, 14));
            serviceCheckBoxes[i].setBackground(new Color(222, 255, 228));
            serviceCheckBoxes[i].setBounds((i % 2 == 0) ? 180 : 380, yCheck, 150, 30);
            contentPane.add(serviceCheckBoxes[i]);
            if (i % 2 != 0) yCheck += 40;
        }
*/
        // Declaration Checkbox
        declare_checkBox = new JCheckBox("I declare that the above details are correct.");
        declare_checkBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        declare_checkBox.setBounds(120, 550, 600, 30);
        declare_checkBox.setBackground(new Color(222, 255, 228));
        contentPane.add(declare_checkBox);

        // Buttons
        
       /* String[] buttonTexts = {"Back", "Submit", "Cancel"};
        JButton[] buttons = {back, submit, cancel};
        int xButton = 110;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonTexts[i]);
            buttons[i].setFont(new Font("Raleway", Font.BOLD, 14));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBounds(xButton, 600, 100, 30);
            contentPane.add(buttons[i]);
            xButton += 250;
        }
        */
        back = new JButton("back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(110, 600, 100, 30);
        contentPane.add(back);
        
        submit = new JButton("submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(360, 600, 100, 30);
        submit.addActionListener(this);
        contentPane.add(submit);
        
        cancel = new JButton("cancel");
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(610, 600, 100, 30);
        contentPane.add(cancel);
        
        // Click the back button
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                /*
                frame.setVisible(false);
*/
                dispose();
            }
        });
        
        // Click the cancel button
        cancel.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });
        
        
        add(contentPane);
        setVisible(true);
        
    }
    
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            
           String accountType = null;
           if(savingAcc_radioButton.isSelected()){
               accountType = "Saving Account";
           }
           else if(fixedDepositAcc_radioButton.isSelected()){
               accountType = "Fixed Deposit Account";
           }
           else if(currentAcc_radioButton.isSelected()){
               accountType = "Current Account";
           }
           else if(recurringDepositAcc_radioButton.isSelected()){
               accountType = "Recurring Deposit Account";
           }
           
           String CardNumber = (String) first;
           String PinNumber  = (String) second;
           
           String servicesReq = " ";
           if(atmCard_checkBox.isSelected()){
               servicesReq = servicesReq + "ATM Card";
           }
           else if(atmCard_checkBox.isSelected()){
               servicesReq = servicesReq +  "Internet Banking";
           }
           else if(mobileBank_checkBox.isSelected()){
               servicesReq = servicesReq +  "Mobile Banking";
           }
           else if(emailAlerts_checkBox.isSelected()){
               servicesReq = servicesReq +  "EMAIL Alerts";
           }
           else if(chequeBook_checkBox.isSelected()){
               servicesReq = servicesReq +  "Cheque Book";
           }
           else if(statement_checkBox.isSelected()){
               servicesReq = servicesReq +  "E-Statement";
           }

           String declare = null ;
           if(declare_checkBox.isSelected()){
               declare = "Declared";
           }
           else{
               declare = "Not Declared";
           }
           
           Double Balance = 00.00 ;
           
            if (accountType == null || CardNumber.equals("") || PinNumber.equals("") || servicesReq.equals("") || declare == null) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                String insertQuery = "INSERT INTO accountPage3 (AccountType, CardNumber, PinNumber, ServicesRequired, Declaration, Balance) VALUES (?, ?, ?, ?, ?, ?)";

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
                   PreparedStatement pstmt = con.prepareStatement(insertQuery);) {
                    // Load MySQL Driver
                   // Class.forName("com.mysql.cj.jdbc.Driver");
        
         
                    // Establish Connection
                    

                    // Setting the values for each placeholder (?)
                    pstmt.setString(1, accountType);
                    pstmt.setString(2, CardNumber);
                    pstmt.setString(3, PinNumber);
                    pstmt.setString(4, servicesReq);
                    pstmt.setString(5, declare);
                    pstmt.setDouble(6, Balance);

                    // Execute the query
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, " ✅  Card No : 4207 1910 6005 "+first+"\n ✅  Pin No    : "+second);
                        
                        // ++++++++++++++++++++++++++++ Mini Statement +++++++++++++++++++++++ >>>             

try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "arun5059");
     Statement stmt = conn.createStatement()) {

    // Get the latest ID
    String getCountIdQuery = "SELECT MAX(id) AS max_id FROM accountPage1";
    ResultSet rs = stmt.executeQuery(getCountIdQuery);
    int latestId = 0;

    if (rs.next()) {
        latestId = rs.getInt("max_id");
    }

    // Get the name using latest ID
    String getNameQuery = "SELECT Name FROM accountPage1 WHERE id = " + latestId;
    rs = stmt.executeQuery(getNameQuery);

    String Name = rs.next() ? rs.getString("Name") : "Unknown_User";

    // Clean the name for use in a table name (e.g., replace spaces with underscores)
    String tableName = Name.replaceAll("\\s+", "_") + "_MiniStatement";

    // Create the MiniStatement table
    String createTableQuery = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "DATE VARCHAR(100), "
            + "PARTICULARS VARCHAR(300), "
            + "DEBIT INT, "
            + "CREDIT INT, "
            + "BALANCE INT"
            + ")";

    stmt.executeUpdate(createTableQuery);

} catch (SQLException ex) {
    ex.printStackTrace();
}
// <<<< ++++++++++++++++++++++++++++ Mini Statement +++++++++++++++++++++++
 

                        dispose();
                        if (createAccount_Page1.instance != null) {
                            createAccount_Page1.instance.dispose();
                        }
                        if (createAccount_Page2.instance != null) {
                            createAccount_Page2.instance.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Some, thing is Error!");
                    }

                    // Close the connection
                    pstmt.close();
                    con.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        }
    }


        
      
    public static void main(String[] args){
        
        new createAccount_Page3();
        
    }
    
}
