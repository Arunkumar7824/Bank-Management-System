 package bank.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.text.*;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

public class createAccount_Page1 extends JFrame implements ActionListener{
    Random random = new Random();
    long first4 = (random.nextLong() % 9000L) + 1000L;
    String first = " " + Math.abs(first4);

    JPanel contentPane;
    JLabel imageLabel, application_Label, formNo_Label, page_Label, personal_De, name_Label, fatherName_Label, gender_Label, dob_Label, emial_Label, maritalStatus_Label, address_Label, city_Label, state_Label, PinCode_Label;
    JTextField textName, textFatherName, email_TextField, address_TextField, city_TextField, state_TextField, PinCode_TextField;
    JRadioButton radio_1, radio_2, radio_3, maried, unMaried, other;
    JButton back, next;
    
    JDateChooser dateChooser;

    ButtonGroup gender_buttonGroup, marital_buttonGroup;

    public static createAccount_Page1 instance; // Add this line
    // Constructor (fixed)
    public createAccount_Page1() {
        super("Application Form");  // This must be called first

        instance = this; // Now this is fine
        setSize(850, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        // Create a panel for content
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, 850, 700);
        contentPane.setBackground(new Color(223, 246, 255));
        contentPane.setLayout(null);
        contentPane.revalidate();
        contentPane.repaint();
        add(contentPane);

        // Image Path
        String imagePath = "C:\\Users\\ARUNKUMAR\\Documents\\NetBeansProjects\\Bank Management System\\src\\bank\\management\\system\\Image\\bankImg.png";

        // Add image label
        imageLabel = createImageLabel(imagePath, 80, 80);
        imageLabel.setBounds(130, 1, 80, 80); // Adjust position inside the panel
        contentPane.add(imageLabel);
        
        application_Label = new JLabel("APPLICATION FORM");
        application_Label.setBounds(250,15,600,40);
        application_Label.setFont(new Font("Raleway",Font.PLAIN, 30));
        contentPane.add(application_Label);
        
        formNo_Label = new JLabel("Form NO : "+ first);
        formNo_Label.setBounds(110,70,600,40);
        formNo_Label.setFont(new Font("Raleway",Font.PLAIN, 19));
        contentPane.add(formNo_Label);
        
        page_Label = new JLabel("Page 1");
        page_Label.setFont(new Font("Raleway",Font.PLAIN,19));
        page_Label.setBounds(658,70,600,30);
        contentPane.add(page_Label);
        
        personal_De = new JLabel("Personal Details");
        personal_De.setFont(new Font("Raleway",Font.BOLD,20));
        personal_De.setBounds(345,70,600,30);
        contentPane.add(personal_De);
       
        //Panel
        JPanel line_Panel = new JPanel();
        line_Panel.setBounds(110,105,608,1);
        line_Panel.setBackground(Color.BLUE);
        contentPane.add(line_Panel);
        
        
        name_Label = new JLabel("Name :");
        name_Label.setFont(new Font("Raleway",Font.PLAIN,18));
        name_Label.setBounds(110,140,100,30);
        contentPane.add(name_Label);
        
        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.PLAIN,14));
        textName.setBounds(320,140,400,28);
        textName.setBackground(new Color(240,251,255));
        contentPane.add(textName);
        
        fatherName_Label = new JLabel("Father's Name :");
        fatherName_Label.setFont(new Font("Raleway",Font.PLAIN,18));
        fatherName_Label.setBounds(110,180,200,30);
        contentPane.add(fatherName_Label);
        
        textFatherName = new JTextField();
        textFatherName.setFont(new Font("Raleway",Font.PLAIN,14));
        textFatherName.setBounds(320,180,400,28);
        textFatherName.setBackground(new Color(240,251,255));
        contentPane.add(textFatherName);
        
        gender_Label = new JLabel("Gender :");
        gender_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        gender_Label.setBounds(110, 220, 200, 30);
        contentPane.add(gender_Label);
        
        radio_1 = new JRadioButton("Male");
        radio_1.setFont(new Font("Raleway", Font.BOLD, 14));
        radio_1.setBounds(320, 220, 60, 30);
        radio_1.setBackground(new Color(223,246,255));
        contentPane.add(radio_1);
        
        radio_2 = new JRadioButton("Female");
        radio_2.setFont(new Font("Raleway", Font.BOLD, 14));
        radio_2.setBounds(440, 220, 90, 30);
        radio_2.setBackground(new Color(223,246,255));
        contentPane.add(radio_2);
        
        radio_3 = new JRadioButton("Others");
        radio_3.setFont(new Font("Raleway", Font.BOLD, 14));
        radio_3.setBounds(625, 220, 90, 30);
        radio_3.setBackground(new Color(223,246,255));
        contentPane.add(radio_3);
        
        gender_buttonGroup = new ButtonGroup();
        gender_buttonGroup.add(radio_1);
        gender_buttonGroup.add(radio_2);
        gender_buttonGroup.add(radio_3);
        
        // Add Date of Birth Label
        dob_Label = new JLabel("Date of Birth :");
        dob_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        dob_Label.setBounds(110, 260, 200, 30);
        contentPane.add(dob_Label);
        
        // JDateChooser 
        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Raleway",Font.BOLD,14));
        dateChooser.setBounds(320,260,400,28);
        dateChooser.setBackground(new Color(240,251,255));
        contentPane.add(dateChooser);
        

       
        emial_Label = new JLabel("Email address :");
        emial_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        emial_Label.setBounds(110, 300, 200, 30);
        contentPane.add(emial_Label);

        email_TextField = new JTextField();
        email_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        email_TextField.setBounds(320,300,400,28);
        email_TextField.setBackground(new Color(240,251,255));
        contentPane.add(email_TextField);
        
        maritalStatus_Label = new JLabel("Marital Status :");
        maritalStatus_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        maritalStatus_Label.setBounds(110, 340, 200, 30);
        contentPane.add(maritalStatus_Label);

        maried = new JRadioButton("Maried");
        maried.setFont(new Font("Raleway", Font.BOLD, 14));
        maried.setBounds(320, 340, 90, 30);
        maried.setBackground(new Color(223,246,255));
        contentPane.add(maried);
        
        unMaried = new JRadioButton("Un Maried");
        unMaried.setFont(new Font("Raleway", Font.BOLD, 14));
        unMaried.setBounds(440, 340, 120, 30);
        unMaried.setBackground(new Color(223,246,255));
        contentPane.add(unMaried);
        
        other = new JRadioButton("Others");
        other.setFont(new Font("Raleway", Font.BOLD, 14));
        other.setBounds(625, 340, 90, 30);
        other.setBackground(new Color(223,246,255));
        contentPane.add(other);
        
        marital_buttonGroup = new ButtonGroup();
        marital_buttonGroup.add(maried);
        marital_buttonGroup.add(unMaried);
        marital_buttonGroup.add(other);

        address_Label = new JLabel("Address :");
        address_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        address_Label.setBounds(110, 380, 200, 30);
        contentPane.add(address_Label);

        address_TextField = new JTextField();
        address_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        address_TextField.setBounds(320,380,400,28);
        address_TextField.setBackground(new Color(240,251,255));
        contentPane.add(address_TextField);
        
        city_Label = new JLabel("City :");
        city_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        city_Label.setBounds(110, 420, 200, 30);
        contentPane.add(city_Label);

        city_TextField = new JTextField();
        city_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        city_TextField.setBounds(320,420,400,28);
        city_TextField.setBackground(new Color(240,251,255));
        contentPane.add(city_TextField);
        
        state_Label = new JLabel("State :");
        state_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        state_Label.setBounds(110, 460, 200, 30);
        contentPane.add(state_Label);

        state_TextField = new JTextField();
        state_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        state_TextField.setBounds(320,460,400,28);
        state_TextField.setBackground(new Color(240,251,255));
        contentPane.add(state_TextField);
        
        PinCode_Label = new JLabel("Pin Code :");
        PinCode_Label.setFont(new Font("Raleway", Font.PLAIN, 18));
        PinCode_Label.setBounds(110, 500, 200, 30);
        contentPane.add(PinCode_Label);

        PinCode_TextField =new JTextField();
        PinCode_TextField.setFont(new Font("Raleway",Font.BOLD,14));
        PinCode_TextField.setBounds(320,500,400,28);
        PinCode_TextField.setBackground(new Color(240,251,255));
        contentPane.add(PinCode_TextField);
        
         // Apply a DocumentFilter to allow only numbers
        ((AbstractDocument) PinCode_TextField.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        
        back = new JButton("back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(320,560,80,30);
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
               
                new createAccount_Page2();
            }
        });
*/
        
        // Click the back button
        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                //new Home();
                dispose();
                //setVisible(false);
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
            String formNo = first;  // You can generate or pass this dynamically
            String name = textName.getText();
            String fatherName = textFatherName.getText();
            String gender = null;
        
        if(radio_1.isSelected()){
            gender = "Male";
        }
        else if(radio_2.isSelected()){
            gender = "Female";
        }
        else if(radio_3.isSelected()){
            gender = "Others";
        }
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String email = email_TextField.getText();
            String marital = null;
        
        if(maried.isSelected()){
            marital = "Married";
        }
        else if(unMaried.isSelected()){
            marital = "Un Married";
        }
        else if(other.isSelected()){
            marital = "Other";
        }
         
            String address = address_TextField.getText();
            String city = city_TextField.getText();
            String state = state_TextField.getText();
            String pinCode = PinCode_TextField.getText();
           

            if (name.equals("") || fatherName.equals("") || gender == null || dob.equals("") || email.equals("") || marital == null || address.equals("") || city.equals("") || state.equals("") || pinCode.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                String insertQuery = "INSERT INTO accountPage1 (Form_Number, name, Father_Name, Gender, DOB, Email, Marital_Status, Address, City, State, PinCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "arun5059");
                   PreparedStatement pstmt = con.prepareStatement(insertQuery);) {
                    // Load MySQL Driver
                   // Class.forName("com.mysql.cj.jdbc.Driver");
        
         
                    // Establish Connection
                    

                    // Setting the values for each placeholder (?)
                    pstmt.setString(1, formNo);
                pstmt.setString(2, name);
                pstmt.setString(3, fatherName);
                pstmt.setString(4, gender);
                pstmt.setString(5, dob);
                pstmt.setString(6, email);
                pstmt.setString(7, marital);
                pstmt.setString(8, address);
                pstmt.setString(9, city);
                pstmt.setString(10, state);
                pstmt.setString(11, pinCode);

                    // Execute the query
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        //JOptionPane.showMessageDialog(this, "Data inserted successfully.");
                        new createAccount_Page2();
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
        new createAccount_Page1();
    }
}

// ====================== Custom filter to allow only numeric input ====================
class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d+")) { // Only allow digits
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d*")) { // Allow replacing only with digits
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
