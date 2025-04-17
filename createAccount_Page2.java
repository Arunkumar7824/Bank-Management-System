
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 // import java.util.Random;
import javax.swing.text.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


        
class createAccount_Page2 extends JFrame implements ActionListener{
    
    JLabel page_Label, personal_De, religion_Label, category_Label, income_Label, educationQuali_Label, occupation_Label, PanNumber_Label, aadharNumber_Label, seniorCitizen_Label, existingAccount_Label;
    JPanel line_Panel, contentPane;
    JComboBox religionComboBox, categoryComboBox, IncomeComboBox, EducationQualification_ComboBox, Occupation_ComboBox;
    JTextField PanNumber_TextField, aadharNumber_TextField;
    JRadioButton seniorCitizen_Yes, seniorCitizen_No, existingAccount_Yes, existingAccount_No;
    ButtonGroup seniorCitizen_buttonGroup, existingAccount_buttonGroup;
    JButton back,next;
    
   public static createAccount_Page2 instance; // Add this line
    
    public createAccount_Page2(){
        super("Application Form");  // This must be called first

        instance = this; // Now this is fine
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 700);
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // Use absolute positioning
        
        
        
        // Create a panel for content
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, 850, 700);
        contentPane.setBackground(new Color(255,249,223));
        contentPane.setLayout(null);
         // Add panel to frame
        add(contentPane);
        
        
        /*
        JLabel application_Label = new JLabel("APPLICATION FORM NO."+ first);
        application_Label.setBounds(160,20,600,40);
        application_Label.setFont(new Font("Raleway",Font.BOLD, 36));
        contentPane.add(application_Label);
        */
        page_Label = new JLabel("Page 2");
        page_Label.setFont(new Font("Raleway",Font.PLAIN,19));
        page_Label.setBounds(658,70,600,30);
        contentPane.add(page_Label);
        
        personal_De = new JLabel("Additional Details");
        personal_De.setFont(new Font("Raleway",Font.PLAIN,19));
        personal_De.setBounds(350,70,600,30);
        contentPane.add(personal_De);
        
        //Panel
        line_Panel = new JPanel();
        line_Panel.setBounds(110,105,610,1);
        line_Panel.setBackground(Color.BLACK);
        contentPane.add(line_Panel);
        
        
        religion_Label = new JLabel("Religion :");
        religion_Label.setFont(new Font("Raleway",Font.PLAIN,18));
        religion_Label.setBounds(110,140,100,30);
        contentPane.add(religion_Label);
        
        // JComboBox with Religion options
        String[] religion = {"Hindu", "Muslim", "Christeen"};
        religionComboBox = new JComboBox<>(religion);
        
        religionComboBox.setFont(new Font("Raleway",Font.PLAIN,14));
        religionComboBox.setBounds(320,140,400,28);
        religionComboBox.setBackground(new Color(255,237,215));
        contentPane.add(religionComboBox);
        
        category_Label = new JLabel("Category :");
        category_Label.setFont(new Font("Raleway",Font.PLAIN,18));
        category_Label.setBounds(110,180,200,30);
        contentPane.add(category_Label);
        
       
        // JComboBox with Religion options
        String[] category = {"BC", "MBC", "SC", "ST", "BMC"};
        categoryComboBox = new JComboBox<>(category);
        
        categoryComboBox.setFont(new Font("Raleway",Font.PLAIN,14));
        categoryComboBox.setBounds(320,180,400,28);
        categoryComboBox.setBackground(new Color(255,237,215));
        contentPane.add(categoryComboBox);
        
        // Add Income Label
        income_Label = new JLabel("Income :");
        income_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        income_Label.setBounds(110, 220, 200, 30);
        contentPane.add(income_Label);
        
        // JComboBox with Religion options
        String[] income = {"Null", "Below 1,50,000", "Below 2,50,000", "Below 5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        IncomeComboBox = new JComboBox<>(income);
        
        IncomeComboBox.setFont(new Font("Raleway",Font.PLAIN,14));
        IncomeComboBox.setBounds(320,220,400,28);
        IncomeComboBox.setBackground(new Color(255,237,215));
        contentPane.add(IncomeComboBox);
        
        
        // Add Education Qualification Label
        educationQuali_Label = new JLabel("Education Qualification :");
        educationQuali_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        educationQuali_Label.setBounds(110, 260, 200, 30);
        contentPane.add(educationQuali_Label);
        
        // JComboBox with Education Qualification options
        String[] educationQuali = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
        EducationQualification_ComboBox = new JComboBox<>(educationQuali);
        
        EducationQualification_ComboBox.setFont(new Font("Raleway",Font.PLAIN,14));
        EducationQualification_ComboBox.setBounds(320,260,400,28);
        EducationQualification_ComboBox.setBackground(new Color(255,237,215));
        contentPane.add(EducationQualification_ComboBox);
       

        occupation_Label = new JLabel("Occupation :");
        occupation_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        occupation_Label.setBounds(110, 300, 200, 30);
        contentPane.add(occupation_Label);
        
        // JComboBox with Occupation options
        String[] occupation = {"Salaried", "Self-Employee", "Business", "Student", "Retired", "Others"};
        Occupation_ComboBox = new JComboBox<>(occupation);
        
        Occupation_ComboBox.setFont(new Font("Raleway",Font.PLAIN,14));
        Occupation_ComboBox.setBounds(320,300,400,28);
        Occupation_ComboBox.setBackground(new Color(255,237,215));
        contentPane.add(Occupation_ComboBox);
        

        PanNumber_Label = new JLabel("PAN Number :");
        PanNumber_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        PanNumber_Label.setBounds(110, 340, 200, 30);
        contentPane.add(PanNumber_Label);

        PanNumber_TextField = new JTextField();
        PanNumber_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        PanNumber_TextField.setBounds(320,340,400,28);
        PanNumber_TextField.setBackground(new Color(255,251,238));
        contentPane.add(PanNumber_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) PanNumber_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        aadharNumber_Label = new JLabel("Aadhar Number :");
        aadharNumber_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        aadharNumber_Label.setBounds(110, 380, 200, 30);
        contentPane.add(aadharNumber_Label);

        aadharNumber_TextField = new JTextField();
        aadharNumber_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        aadharNumber_TextField.setBounds(320,380,400,28);
        aadharNumber_TextField.setBackground(new Color(255,251,238));
        contentPane.add(aadharNumber_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) aadharNumber_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        seniorCitizen_Label = new JLabel("Senior Citizen :");
        seniorCitizen_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        seniorCitizen_Label.setBounds(110, 420, 200, 30);
        contentPane.add(seniorCitizen_Label);
        
        seniorCitizen_Yes = new JRadioButton("Yes");
        seniorCitizen_Yes.setFont(new Font("Raleway", Font.BOLD, 14));
        seniorCitizen_Yes.setBounds(320, 420, 60, 30);
        seniorCitizen_Yes.setBackground(new Color(255,249,223));
        contentPane.add(seniorCitizen_Yes);
        
        seniorCitizen_No = new JRadioButton("No");
        seniorCitizen_No.setFont(new Font("Raleway", Font.BOLD, 14));
        seniorCitizen_No.setBounds(470, 420, 90, 30);
        seniorCitizen_No.setBackground(new Color(255,249,223));
        contentPane.add(seniorCitizen_No);
        
        seniorCitizen_buttonGroup = new ButtonGroup();
        seniorCitizen_buttonGroup.add(seniorCitizen_Yes);
        seniorCitizen_buttonGroup.add(seniorCitizen_No);
        
        existingAccount_Label = new JLabel("Existing Account :");
        existingAccount_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        existingAccount_Label.setBounds(110, 470, 200, 30);
        contentPane.add(existingAccount_Label);
        
        existingAccount_Yes = new JRadioButton("Yes");
        existingAccount_Yes.setFont(new Font("Raleway", Font.BOLD, 14));
        existingAccount_Yes.setBounds(320, 470, 60, 30);
        existingAccount_Yes.setBackground(new Color(255,249,223));
        contentPane.add(existingAccount_Yes);
        
        existingAccount_No = new JRadioButton("No");
        existingAccount_No.setFont(new Font("Raleway", Font.BOLD, 14));
        existingAccount_No.setBounds(470, 470, 90, 30);
        existingAccount_No.setBackground(new Color(255,249,223));
        contentPane.add(existingAccount_No);
        
        existingAccount_buttonGroup = new ButtonGroup();
        existingAccount_buttonGroup.add(existingAccount_Yes);
        existingAccount_buttonGroup.add(existingAccount_No);
        
        
        back = new JButton("back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(320, 560, 80, 30);
        contentPane.add(back);
        
        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(640,560,80,30);
        next.addActionListener(this);
        contentPane.add(next);
        
        /*
        // Click the next button
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               
                createAccount_Page3 varAcc = new createAccount_Page3();
                varAcc.createAccountFunctionPage3();
               new createAccount_Page3();
            }
        });
        */
        
        // Click the back button
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                /*
                frame.setVisible(false);
*/
                dispose();
            }
        });
        
        // Set frame visible
        setVisible(true);
    }

    // Method to create resized images
    private static JLabel createImageLabel(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(resizedImage));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
           String religion = (String) religionComboBox.getSelectedItem();
           String category = (String) categoryComboBox.getSelectedItem();
           String income = (String) IncomeComboBox.getSelectedItem();
           String educationQuali = (String) EducationQualification_ComboBox.getSelectedItem();
           String occupation = (String) Occupation_ComboBox.getSelectedItem();
           String PanNumber = PanNumber_TextField.getText();
           String aadharNumber = aadharNumber_TextField.getText();
           
           String seniorCritizen = null;
           if((seniorCitizen_Yes.isSelected())){
               seniorCritizen = "Yes";
           }
           else if((seniorCitizen_No.isSelected())){
               seniorCritizen = "No";
           }
           
           String existingAccount = null;
           if((existingAccount_Yes.isSelected())){
               existingAccount = "Yes";
           }
           else if((existingAccount_No.isSelected())){
               existingAccount = "No";
           }

            if (religion.equals("") || category.equals("") || income.equals("") || educationQuali.equals("") || occupation.equals("") || PanNumber.equals("") || aadharNumber.equals("") || seniorCritizen == null || existingAccount == null) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                String insertQuery = "INSERT INTO accountPage2 (Religion, Category, Income, EducationQualification, Occupation, PanNumber, AadharNumber, SeniorCritizen, ExistingAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
                   PreparedStatement pstmt = con.prepareStatement(insertQuery);) {
                    // Load MySQL Driver
                   // Class.forName("com.mysql.cj.jdbc.Driver");
        
         
                    // Establish Connection
                    

                    // Setting the values for each placeholder (?)
                    pstmt.setString(1, religion);
                pstmt.setString(2, category);
                pstmt.setString(3, income);
                pstmt.setString(4, educationQuali);
                pstmt.setString(5, occupation);
                pstmt.setString(6, PanNumber);
                pstmt.setString(7, aadharNumber);
                pstmt.setString(8, seniorCritizen);
                pstmt.setString(9, existingAccount);

                    // Execute the query
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        //JOptionPane.showMessageDialog(this, "Data inserted successfully.");
                        new createAccount_Page3();
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
        new createAccount_Page2();
    }
}
