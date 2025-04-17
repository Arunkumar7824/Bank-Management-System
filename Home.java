
package bank.management.system;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Home extends JFrame {
    JPanel panel;
    JLabel label_1, label_2, label_3, AccountText, AtmText;
    JButton button1, button2;

    Home() {
        super("SBI Home Page");
        
        getContentPane().setBackground(Color.WHITE);
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        label_1 = createImageLabel("C:\\Users\\ARUNKUMAR\\Documents\\NetBeansProjects\\Bank Management System\\src\\bank\\management\\system\\Image\\bankImage.jpg", 250, 250);
        label_2 = createImageLabel("C:\\Users\\ARUNKUMAR\\Documents\\NetBeansProjects\\Bank Management System\\src\\bank\\management\\system\\Image\\AccountImage.png", 200, 200);
        label_3 = createImageLabel("C:\\Users\\ARUNKUMAR\\Documents\\NetBeansProjects\\Bank Management System\\src\\bank\\management\\system\\Image\\AtmImage.png", 230, 230);

        AccountText = createText("Create Account Here");
        AtmText = createText("Use ATM Here");

        button1 = createStyledButton("Click Here");
        button2 = createStyledButton("Click Here");

        button1.addActionListener(e -> {
            // Open account creation screen
            new createAccount_Page1();
            //dispose();
        });

        button2.addActionListener(e -> {
            // Open ATM functionality
            new userATM();
        });

        panel.add(label_1, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(label_2, gbc);

        gbc.gridx = 1;
        panel.add(label_3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(AccountText, gbc);

        gbc.gridx = 1;
        panel.add(AtmText, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(button1, gbc);

        gbc.gridx = 1;
        panel.add(button2, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private static JLabel createImageLabel(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image img = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    public static JLabel createText(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.BLACK);
        return label;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(19, 86, 243));
            }
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(81, 142, 247));
            }
        });

        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(81, 142, 247));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(10, 22, 10, 22));

        return button;
    }

    public static void main(String[] args) {
        new Home();
    }
}

